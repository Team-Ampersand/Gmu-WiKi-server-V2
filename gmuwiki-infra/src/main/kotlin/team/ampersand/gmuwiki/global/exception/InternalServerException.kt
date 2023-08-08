package team.ampersand.gmuwiki.global.exception

import team.ampersand.gmuwiki.common.error.GmuwikiException
import team.ampersand.gmuwiki.global.error.GlobalErrorCode

object InternalServerException : GmuwikiException(
    GlobalErrorCode.INTERNAL_SERVER_ERROR
)