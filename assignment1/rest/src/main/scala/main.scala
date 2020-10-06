package sc.buaa.assign1.restful

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.context.annotation.Configuration

object Main extends App {
  override def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[PersonRestServices])
  }
}

@SpringBootApplication
class PersonRestServices {}
