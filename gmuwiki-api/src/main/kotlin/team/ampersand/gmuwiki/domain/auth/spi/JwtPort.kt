package team.ampersand.gmuwiki.domain.auth.spi

import team.ampersand.gmuwiki.domain.auth.dto.TokenResponse
import team.ampersand.gmuwiki.domain.auth.model.constant.Authority
import java.util.*

interface JwtPort {
    fun receiveToken(userId: UUID,authority: Authority): TokenResponse
}