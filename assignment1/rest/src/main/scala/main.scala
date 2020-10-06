package sc.buaa.assign1.restful

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.context.annotation.Bean
//import org.springframework.web.servlet.config.annotation.{
//  CorsRegistry,
//  WebMvcConfigurer,
//  WebMvcConfigurerAdapter
//}
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.context.annotation.Configuration

object PersonRestServices extends App {
  override def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[SpringBootIntegration])
  }
}

@SpringBootApplication
@Configuration
class SpringBootIntegration {
  //@Bean
  //def corsConfigurer(): WebMvcConfigurer = {
  //  new WebMvcConfigurerAdapter() {
  //    override def addCorsMappings(registry: CorsRegistry): Unit = {
  //      registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
  //    }
  //  }
  //}

}
