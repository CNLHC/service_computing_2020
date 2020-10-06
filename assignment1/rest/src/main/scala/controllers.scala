package sc.buaa.assign1.restful

import javax.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{
  GetMapping,
  PostMapping,
  RequestBody,
  RequestMapping,
  ResponseBody,
  RestController
}

@RequestMapping(path = Array("/person"))
@RestController
class PersonController {

  @GetMapping(path = Array("/name"))
  @ResponseBody
  def getName(response: HttpServletResponse): Unit = {
    response.setStatus(HttpStatus.OK.value())
    response.getWriter.println("SomeName")
    response.getWriter.flush()
    response.getWriter.close()
  }

  @GetMapping(path = Array("/age"))
  @ResponseBody
  def getAge(response: HttpServletResponse): Unit = {
    response.setStatus(HttpStatus.OK.value())
    response.getWriter.println("22")
    response.getWriter.flush()
    response.getWriter.close()
  }

  @GetMapping(path = Array("/gender"))
  @ResponseBody
  def getGender(response: HttpServletResponse): Unit = {
    response.setStatus(HttpStatus.OK.value())
    response.getWriter.println("gender")
    response.getWriter.flush()
    response.getWriter.close()
  }
}
