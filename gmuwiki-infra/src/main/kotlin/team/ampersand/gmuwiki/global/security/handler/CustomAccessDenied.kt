package team.ampersand.gmuwiki.global.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import team.ampersand.gmuwiki.global.security.exception.InvalidRoleException

@Component
class CustomAccessDenied : AccessDeniedHandler {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException) {

        log.info("==============Access Denied===============")
        throw InvalidRoleException
    }
}