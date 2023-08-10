package team.ampersand.gmuwiki.domain.user.model

import team.ampersand.gmuwiki.domain.auth.model.constant.Authority
import java.util.*

data class User(
    val id: UUID,
    val email: String,
    val name: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?,
    val profileImageUrl: String,
    val authority: Authority
)
