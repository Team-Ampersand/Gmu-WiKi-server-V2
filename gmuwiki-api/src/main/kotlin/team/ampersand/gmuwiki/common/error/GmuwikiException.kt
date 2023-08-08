package team.ampersand.gmuwiki.common.error

abstract class GmuwikiException(
    private val errorProperty: ErrorProperty
) : RuntimeException() {

    override fun fillInStackTrace() = this
}