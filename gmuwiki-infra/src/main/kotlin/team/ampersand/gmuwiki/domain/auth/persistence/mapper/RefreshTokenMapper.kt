package team.ampersand.gmuwiki.domain.auth.persistence.mapper

import org.springframework.stereotype.Component
import team.ampersand.gmuwiki.domain.auth.model.RefreshToken
import team.ampersand.gmuwiki.domain.auth.persistence.entity.RefreshTokenEntity
import team.ampersand.gmuwiki.global.mapper.GenericMapper

@Component
class RefreshTokenMapper : GenericMapper<RefreshToken, RefreshTokenEntity> {

    override fun toDomain(entity: RefreshTokenEntity?): RefreshToken? {
        return entity?.let {
            RefreshToken(
                token = it.token,
                userId = it.userId,
                authority = it.authority,
                expiredAt = it.expiredAt
            )
        }
    }

    override fun toEntity(domain: RefreshToken): RefreshTokenEntity {
        return RefreshTokenEntity(
            token = domain.token,
            userId = domain.userId,
            authority = domain.authority,
            expiredAt = domain.expiredAt
        )
    }
}