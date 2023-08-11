package team.ampersand.gmuwiki.global.security.token

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.ampersand.gmuwiki.domain.auth.dto.TokenResponse
import team.ampersand.gmuwiki.domain.auth.model.RefreshToken
import team.ampersand.gmuwiki.domain.auth.model.constant.Authority
import team.ampersand.gmuwiki.domain.auth.spi.CommandRefreshTokenSpi
import team.ampersand.gmuwiki.domain.auth.spi.JwtPort
import team.ampersand.gmuwiki.global.security.SecurityProperties
import java.time.LocalDateTime
import java.util.*

@Component
class GenerateJwtAdapter(
    private val securityProperties: SecurityProperties,
    private val commandRefreshTokenSpi: CommandRefreshTokenSpi
) : JwtPort{

    override fun receiveToken(userId: UUID, authority: Authority): TokenResponse =
        TokenResponse(
            accessToken = generateAccessToken(userId, authority),
            accessExpiredAt = LocalDateTime.now().withNano(0).plusSeconds(securityProperties.accessExp.toLong()),
            refreshToken = generateRefreshToken(userId, authority),
            refreshExpiredAt = LocalDateTime.now().withNano(0).plusSeconds(securityProperties.refreshExp.toLong()),
        )

    private fun generateAccessToken(userId: UUID, authority: Authority) =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.secretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtProperties.ACCESS)
            .setId(userId.toString())
            .claim(JwtProperties.AUTHORITY, authority.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(userId: UUID, authority: Authority) : String {
        val token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS512,securityProperties.secretKey)
            .setHeaderParam(Header.JWT_TYPE,JwtProperties.REFRESH)
            .setId(userId.toString())
            .claim(JwtProperties.AUTHORITY,authority.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.refreshExp * 1000))
            .compact()

        val refreshToken = RefreshToken(
            token = token,
            userId = userId,
            authority = authority,
            expiredAt = securityProperties.refreshExp
        )
        commandRefreshTokenSpi.save(refreshToken)

        return token
    }

}