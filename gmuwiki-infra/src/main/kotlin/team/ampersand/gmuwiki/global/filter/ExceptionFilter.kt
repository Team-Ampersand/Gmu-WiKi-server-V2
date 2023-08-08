package team.ampersand.gmuwiki.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import io.sentry.Sentry
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import team.ampersand.gmuwiki.common.error.ErrorProperty
import team.ampersand.gmuwiki.common.error.GmuwikiException
import team.ampersand.gmuwiki.global.error.ErrorResponse
import team.ampersand.gmuwiki.global.exception.InternalServerException
import java.nio.charset.StandardCharsets

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val result = runCatching {
            filterChain.doFilter(request, response)
        }

        result.onFailure { e ->
            when (e.cause) {
                is GmuwikiException -> {
                    errorToJson((e.cause as GmuwikiException).errorProperty, response)
                }
                else -> {
                    errorToJson(InternalServerException.errorProperty, response)
                }
            }
            Sentry.captureException(e)
        }
    }

    private fun errorToJson(errorProperty: ErrorProperty,response: HttpServletResponse) {
        response.status = errorProperty.status()
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(ErrorResponse.of(errorProperty)))
    }
}