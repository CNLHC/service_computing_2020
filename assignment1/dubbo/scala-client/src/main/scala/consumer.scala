package sc.buaa.person

import org.springframework.context.support.ClassPathXmlApplicationContext;
import scopt.OParser

case class Opt(
	method: String = "GetName",
	name : String =  "CNLHC",
	age : Int = 22,
	gender: Boolean = false,
)


trait  PersonService{

  def GetName(): String
  def SetName(name:String): Boolean

  def GetAge():Int
  def SetAge(age: Int): Boolean

  def GetGender(): Boolean
  def SetGender(gender: Boolean): Boolean

} 


object ConsumerApp extends App {
	
  override def main( args: Array[String]) = {
	val builder = OParser.builder[Opt]
	val parser1 = {
		import builder._
		OParser.sequence(
		  programName("scala-client"),
		  head("scala-client", "4.x"),
		  opt[String]('m', "method")
			.action((x, c) => c.copy(method = x))
			.text("method to invoke"),
		  opt[String]('n', "name")
			.action((x, c) => c.copy(name= x))
			.text("name"),
		  opt[Int]('a', "age")
			.action((x, c) => c.copy(age= x))
			.text("Age"),
		  opt[Boolean]('g', "gender")
			.action((x, c) => c.copy(gender = x))
			.text("Gender"),
		)
	}
	OParser.parse(parser1,args,Opt()) match{
	  case  Some(opt:Opt) =>{
		val context = new ClassPathXmlApplicationContext("provider.xml")
		context.start()
		val consumer = context.getBean(classOf[PersonService])
		println(f"methods: ${opt.method}")
		opt.method match {
		case "GetName"=>
			val remote_name = consumer.GetName()
			println(f"name: $remote_name")
		case "GetAge"=>
			val remote_age= consumer.GetAge()
			println(f"age: $remote_age")
		case "GetGender"=>
			val remote_gender= consumer.GetGender()
			println(f"gender: $remote_gender%b")
		case "SetName"=>
			consumer.SetName(opt.name)
			println(f"set name to ${opt.name}")
		case "SetAge"=>
			consumer.SetAge(opt.age)
			println(f"set age to ${opt.age}")
		case "SetGender"=>
			consumer.SetGender(opt.gender)
			println(f"set genderto ${opt.gender}")
		case m =>
			println(f"$m is not an available procedure!\n")
		}
	  }
	  case _ =>{}
	}
    System.exit(0)
  }
}
