package team.ampersand.gmuwiki.global.security.exception

import team.ampersand.gmuwiki.common.error.GmuwikiException
import team.ampersand.gmuwiki.global.security.exception.error.SecurityErrorCode

object InvalidRoleException : GmuwikiException(SecurityErrorCode.INVALID_ROLE)