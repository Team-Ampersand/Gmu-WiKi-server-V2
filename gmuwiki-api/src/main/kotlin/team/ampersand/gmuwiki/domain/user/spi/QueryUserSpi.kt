package team.ampersand.gmuwiki.domain.user.spi

import team.ampersand.gmuwiki.domain.user.model.User
import java.util.*

interface QueryUserSpi {
    fun queryUserById(id: UUID): User?
    fun queryUserByEmail(email: String): User?
}