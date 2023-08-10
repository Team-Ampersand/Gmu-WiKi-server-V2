package team.ampersand.gmuwiki.domain.auth.spi

import team.ampersand.gmuwiki.domain.auth.model.RefreshToken

interface CommandRefreshTokenSpi {
    fun save(refreshToken: RefreshToken): RefreshToken
}