package team.ampersand.gmuwiki.global.security

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

@ConfigurationProperties(prefix = "secret")
class SecurityProperties(
    secretKey: String,
    val accessExp: Int,
    val refreshExp: Int
) {
    val secretKey: String = Base64.getEncoder().encodeToString(secretKey.toByteArray())
}
