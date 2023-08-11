package team.ampersand.gmuwiki.domain.auth.model

import team.ampersand.gmuwiki.common.annotation.Aggregate
import team.ampersand.gmuwiki.domain.auth.model.constant.Authority
import java.util.*

@Aggregate
data class RefreshToken(
    val token: String,
    val userId: UUID,
    val authority: Authority,
    val expiredAt: Int
)