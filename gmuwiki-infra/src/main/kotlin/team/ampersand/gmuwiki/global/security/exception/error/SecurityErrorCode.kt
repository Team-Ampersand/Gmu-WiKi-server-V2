package team.ampersand.gmuwiki.global.security.exception.error

import team.ampersand.gmuwiki.common.error.ErrorProperty

enum class SecurityErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    UNEXPECTED_TOKEN(401, "Unexpected Token"),
    INVALID_ROLE(401, "Invalid Role"),

    FORBIDDEN(403, "Forbidden")
    ;

    override fun status(): Int = status

    override fun message(): String = message
}