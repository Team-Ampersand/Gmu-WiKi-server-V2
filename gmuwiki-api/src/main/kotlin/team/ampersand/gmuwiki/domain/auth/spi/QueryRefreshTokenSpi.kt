package team.ampersand.gmuwiki.domain.auth.spi

import team.ampersand.gmuwiki.domain.auth.model.RefreshToken

interface QueryRefreshTokenSpi {
    fun queryRefreshTokenByToken(token: String): RefreshToken?
}