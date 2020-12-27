import com.ibm.wala.classLoader.IClass
import com.ibm.wala.ipa.callgraph._
import com.ibm.wala.ipa.callgraph.impl.DefaultEntrypoint
import com.ibm.wala.ipa.callgraph.pruned.PruningPolicy
import com.ibm.wala.ipa.cha.{ClassHierarchyFactory, IClassHierarchy}
import com.ibm.wala.util.config.{AnalysisScopeReader, FileOfClasses}
import com.ibm.wala.util.graph.Graph
import com.ibm.wala.util.graph.impl.SlowSparseNumberedGraph
import com.ibm.wala.viz.{DotUtil, NodeDecorator}
import scopt.OParser

import java.io.{ByteArrayInputStream, File}
import scala.collection.JavaConverters._

object App {
  private val EXCLUSIONS = "java\\/awt\\/.*\n" +
    "javax\\/swing\\/.*\n" +
    "sun\\/awt\\/.*\n" +
    "sun\\/swing\\/.*\n" +
    "com\\/sun\\/.*\n" +
    "sun\\/.*\n" +
    "org\\/netbeans\\/.*\n" +
    "org\\/openide\\/.*\n" +
    "com\\/ibm\\/crypto\\/.*\n" +
    "com\\/ibm\\/security\\/.*\n" +
    "org\\/apache\\/xerces\\/.*\n" +
    "java\\/security\\/.*\n" +
    "java\\/util/.*\n";
  private var KEY_WORDS = List("Replica", "Replication")
  private var cfg: Config = new Config()

  def main(args: Array[String]) {
    val builder = OParser.builder[Config]
    val parser1 = {
      import builder._
      OParser.sequence(
        programName("wala-driver"),
        opt[String]('i', "input")
          .required()
          .action((x, c) => c.copy(InputDir = x))
          .text("input directory"),
        opt[String]('o', "output")
          .required()
          .action((x, c) => c.copy(OutputDir = x))
          .text("output directory"),
      )
    }
    OParser.parse(parser1, args, Config()) match {
      case Some(c) => {
        cfg = c
      }
      case _ => {
        println("invalid arguments")
        sys.exit(0)
      }
    }
    val dir = new File(cfg.InputDir)
    for (jar <- dir.listFiles()) {
      println(jar)
      analysis(jar.getAbsolutePath)
    }

  }

  def analysis(target: String): Unit = {
    println(s"task: $target")
    val scope = AnalysisScopeReader.makeJavaBinaryAnalysisScope(target, null);
    scope.setExclusions(new FileOfClasses(new ByteArrayInputStream(App.EXCLUSIONS.getBytes("UTF-8"))));
    val cha = ClassHierarchyFactory.make(scope);
    val classes = filterByKeyWords(cha, KEY_WORDS).toList
    val len = classes.length
    println(s"%target keywords count: $len")
    val cg = buildTypeGraph(cha)
    val fp = new File(target)
    toDot(cg, s"${len}_${fp.getName()}")
  }

  def toDot(tg: Graph[IClass], target: String) = {
    val dotFile = new File(s"${cfg.OutputDir}/WALA_$target.dot").getAbsolutePath
    val render = new Render
    DotUtil.writeDotFile(tg, render, s"Keywords Analysis for $target", dotFile)
  }

  def filterByKeyWords(cha: IClassHierarchy, keyWords: List[String]) = {
    cha.iterator().asScala
      .filter((e) =>
        keyWords.map(e.toString().contains).reduce((a, b) => a || b)
      )
  }

  def buildTypeGraph(cha: IClassHierarchy): Graph[IClass] = {
    val res: Graph[IClass] = SlowSparseNumberedGraph.make()
    val book = scala.collection.mutable.Map[IClass, Boolean]()

    for (node <- cha.iterator().asScala) {
      res.addNode(node)
    }

    for (node <- cha.iterator().asScala.filter((e) => checkKeyWords(e.getName().toString()))) {
      book += (node -> true)
      for (x <- cha.getImmediateSubclasses(node).asScala) {
        book += (x -> true)
        res.addEdge(node, x)
      }
      if (node.isInterface()) {
        for (x <- cha.getImplementors(node.getReference()).asScala) {
          book += (x -> true)
          res.addEdge(node, x)
        }
      }
    }
    cha.iterator().asScala.filter(book.get(_).isEmpty).foreach(res.removeNode(_))
    res
  }

  def checkKeyWords(str: String): Boolean = KEY_WORDS.map(str.contains).reduce(_ || _)

  def makeEntry(
                 cha: IClassHierarchy,
                 entryClass: List[IClass]
               ): java.util.List[DefaultEntrypoint] = {
    entryClass
      .map(_.getAllMethods().asScala)
      .flatten
      .filter(_.isPublic())
      .filter(_.isInit())
      .map(new DefaultEntrypoint(_, cha))
      .asJava
  }

  class Render extends NodeDecorator[IClass] {
    override def getLabel(n: IClass): String =
      n.getName().toString().replace('/', '.')
  }

  case class Config(
                     InputDir: String = "jars",
                     OutputDir: String = "results"
                   )

  class PruneForKeywords(var keywords: List[String]) extends PruningPolicy {
    override def check(n: CGNode): Boolean = {
      val base = n.getMethod().getDeclaringClass().getName().toString();
      val res = keywords.map(base.contains(_)).reduce((a, b) => a || b);
      return res
    }
  }

}
