package sc.buaa.assign1.restful
import scala.concurrent.ExecutionContext.Implicits.global
import javax.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

import slick.driver.SQLiteDriver.api._
import org.springframework.web.bind.annotation.{
  GetMapping,
  PostMapping,
  RequestBody,
  RequestMapping,
  ResponseBody,
  RestController
}
import scala.util.{Failure, Success, Try}
import org.springframework.scheduling.annotation.EnableAsync
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import org.springframework.web.bind.annotation.RequestParam

@RequestMapping(path = Array("/person"))
@RestController
class PersonController {

  def getField[F, G, H](
      selector: Tables.People => F
  )(implicit
      shape: Shape[_ <: slick.lifted.FlatShapeLevel, F, G, H]
  ): G = {
    val db = Database.forConfig("local_sqlite")
    val person = TableQuery[Tables.People]
    Await.result(
      db.run(
        person.map(selector).result.head
      ),
      Duration.Inf
    )
  }

  def respOk[T <% String](response: HttpServletResponse, value: T) = {
    response.setStatus(HttpStatus.OK.value())
    response.getWriter.println(value)
    response.getWriter.flush()
    response.getWriter.close()
  }

  @GetMapping(path = Array("/name"))
  @ResponseBody
  def getName(response: HttpServletResponse): Unit = {
    getField(_.name) match {
      case Some(e) => respOk(response, e)
      case _       => println("error")
    }
  }

  @GetMapping(path = Array("/age"))
  @ResponseBody
  def getAge(response: HttpServletResponse): Unit = {
    getField(_.age) match {
      case Some(e) => respOk(response, e.toString())
      case _       => println("error")
    }
  }

  @GetMapping(path = Array("/gender"))
  @ResponseBody
  def getGender(response: HttpServletResponse): Unit = {
    getField(_.gender) match {
      case Some(e) => respOk(response, if (e == 0) "false" else "true")
      case _       => println("error")
    }
  }

  @PostMapping(path = Array("/name"))
  @ResponseBody
  def setName(
      response: HttpServletResponse,
      @RequestParam name: String
  ): Unit = {
    val db = Database.forConfig("local_sqlite")
    val person = TableQuery[Tables.People]
    Await.result(
      db.run(
        person.filter(_.id === 1).map(_.name).update(Some(name))
      ),
      Duration.Inf
    );
  }

  @PostMapping(path = Array("/age"))
  @ResponseBody
  def setAge(
      response: HttpServletResponse,
      @RequestParam age: Int
  ): Unit = {
    val db = Database.forConfig("local_sqlite")
    val person = TableQuery[Tables.People]
    Await.result(
      db.run(
        person.filter(_.id === 1).map(_.age).update(Some(age))
      ),
      Duration.Inf
    );
  }

  @PostMapping(path = Array("/gender"))
  @ResponseBody
  def setGender(
      response: HttpServletResponse,
      @RequestParam gender: Boolean
  ): Unit = {
    val db = Database.forConfig("local_sqlite")
    val person = TableQuery[Tables.People]
    println(111, gender)
    Await.result(
      db.run(
        person
          .filter(_.id === 1)
          .map(_.gender)
          .update(if (gender) Some(1) else Some(0))
      ),
      Duration.Inf
    );
  }
}
