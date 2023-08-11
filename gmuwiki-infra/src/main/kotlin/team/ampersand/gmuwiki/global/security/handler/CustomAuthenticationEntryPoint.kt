package team.ampersand.gmuwiki.global.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import team.ampersand.gmuwiki.global.security.exception.ForbiddenException

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          authException: AuthenticationException) {
        log.info("==========AuthenticationEntryPoint===========")
        throw ForbiddenException
    }
}