package team.ampersand.gmuwiki.common.spi

import java.util.*

interface SecuritySpi {

    fun isAuthenticated(): Boolean

    fun getCurrentUserId(): UUID

    fun encodePassword(password: String): String
}