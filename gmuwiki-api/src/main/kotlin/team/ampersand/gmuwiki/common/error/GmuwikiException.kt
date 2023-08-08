package team.ampersand.gmuwiki.common.error

abstract class GmuwikiException(
    val errorProperty: ErrorProperty
) : RuntimeException() {

    override fun fillInStackTrace() = this
}