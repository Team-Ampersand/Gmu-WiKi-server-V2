package team.ampersand.gmuwiki.domain.auth.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.ampersand.gmuwiki.domain.auth.model.RefreshToken

interface RefreshTokenRepository : CrudRepository<String, RefreshToken>