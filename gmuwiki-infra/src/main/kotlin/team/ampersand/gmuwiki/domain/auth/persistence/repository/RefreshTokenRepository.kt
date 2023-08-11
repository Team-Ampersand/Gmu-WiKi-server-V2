package team.ampersand.gmuwiki.domain.auth.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.ampersand.gmuwiki.domain.auth.persistence.entity.RefreshTokenEntity

interface RefreshTokenRepository : CrudRepository<RefreshTokenEntity, String>