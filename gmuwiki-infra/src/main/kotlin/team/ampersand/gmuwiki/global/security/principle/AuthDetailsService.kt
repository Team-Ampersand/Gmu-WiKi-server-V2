package team.ampersand.gmuwiki.global.security.principle

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import team.ampersand.gmuwiki.domain.user.persistence.mapper.UserMapper
import team.ampersand.gmuwiki.domain.user.spi.QueryUserSpi
import team.ampersand.gmuwiki.global.security.exception.InvalidTokenException
import java.util.*

class AuthDetailsService(
    private val queryUserSpi: QueryUserSpi,
    private val userMapper: UserMapper
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = queryUserSpi.queryUserById(UUID.fromString(username))
            ?: throw InvalidTokenException

        return AuthDetails(
            userMapper.toEntity(user)
        )
    }
}