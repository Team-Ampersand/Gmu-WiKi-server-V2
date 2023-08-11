package team.ampersand.gmuwiki.global.security.exception

import team.ampersand.gmuwiki.common.error.GmuwikiException
import team.ampersand.gmuwiki.global.security.exception.error.SecurityErrorCode

object ExpiredTokenException : GmuwikiException(SecurityErrorCode.EXPIRED_TOKEN)
