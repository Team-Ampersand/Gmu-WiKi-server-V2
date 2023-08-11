package team.ampersand.gmuwiki.global.security.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import team.ampersand.gmuwiki.global.exception.InternalServerException
import team.ampersand.gmuwiki.global.security.SecurityProperties
import team.ampersand.gmuwiki.global.security.exception.ExpiredTokenException
import team.ampersand.gmuwiki.global.security.exception.InvalidTokenException
import team.ampersand.gmuwiki.global.security.exception.UnexpectedTokenException
import team.ampersand.gmuwiki.global.security.principle.AuthDetailsService

@Component
class JwtParser(
    private val securityProperties: SecurityProperties,
    private val authDetailsService: AuthDetailsService
) {

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        if(claims.header[Header.JWT_TYPE] != JwtProperties.ACCESS){
            throw InvalidTokenException
        }

        val userDetails = getDetails(claims.body)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try{
            Jwts.parserBuilder()
                .setSigningKey(securityProperties.secretKey)
                .build()
                .parseClaimsJws(token)
        } catch(e: Exception){
            when(e){
                is InvalidClaimException -> throw InvalidTokenException
                is ExpiredJwtException -> throw  ExpiredTokenException
                is JwtException -> throw UnexpectedTokenException
                else -> throw InternalServerException
            }
        }
    }

    private fun getDetails(body: Claims): UserDetails =
        authDetailsService.loadUserByUsername(body.id)
}