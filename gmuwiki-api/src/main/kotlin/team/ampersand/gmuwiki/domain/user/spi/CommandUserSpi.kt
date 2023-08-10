package team.ampersand.gmuwiki.domain.user.spi

import team.ampersand.gmuwiki.domain.user.model.User
import java.util.UUID

interface CommandUserSpi {
    fun save(user: User): User
    fun delete(user: User)
}