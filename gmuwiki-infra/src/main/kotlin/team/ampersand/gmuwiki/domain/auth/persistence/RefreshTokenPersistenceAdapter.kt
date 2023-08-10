package team.ampersand.gmuwiki.domain.auth.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.ampersand.gmuwiki.domain.auth.model.RefreshToken
import team.ampersand.gmuwiki.domain.auth.persistence.mapper.RefreshTokenMapper
import team.ampersand.gmuwiki.domain.auth.persistence.repository.RefreshTokenRepository
import team.ampersand.gmuwiki.domain.auth.spi.RefreshTokenSpi

@Component
class RefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
) : RefreshTokenSpi{
    override fun queryRefreshTokenByToken(token: String): RefreshToken? =
        refreshTokenRepository.findByIdOrNull(token)
            .let { refreshTokenMapper.toDomain(it) }

    override fun save(refreshToken: RefreshToken): RefreshToken =
        refreshTokenMapper.toEntity(refreshToken)
            .let { refreshTokenRepository.save(it) }
            .let { refreshTokenMapper.toDomain(it)!! }

}