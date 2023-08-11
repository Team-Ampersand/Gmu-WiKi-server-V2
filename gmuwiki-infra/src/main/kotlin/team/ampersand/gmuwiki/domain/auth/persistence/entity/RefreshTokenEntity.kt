package team.ampersand.gmuwiki.domain.auth.persistence.entity

import jakarta.persistence.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import team.ampersand.gmuwiki.domain.auth.model.constant.Authority
import java.util.*

@RedisHash("tbl_refresh_token")
class RefreshTokenEntity(
    @Id
    val token: String,

    val userId: UUID,

    val authority: Authority,

    @TimeToLive
    val expiredAt: Int
)