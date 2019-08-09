package bupt.dawsonlee1790.i18n

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest


@SpringBootApplication
class I18nApplication

fun main(args: Array<String>) {
    runApplication<I18nApplication>(*args)
}

@RestController
class I18nController {

    @GetMapping("/home")
    fun home(): String? {
        throw Exception("错误")
    }
}

@ControllerAdvice
@ResponseBody
class I18nExceptionHandler {

    @Autowired
    private lateinit var messageSource: MessageSource

    @Autowired
    private lateinit var httpServletRequest: HttpServletRequest

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handler1(e: Exception): String? {
        return messageSource.getMessage(e.message ?: "", null, e.message, httpServletRequest.locale)
    }

}