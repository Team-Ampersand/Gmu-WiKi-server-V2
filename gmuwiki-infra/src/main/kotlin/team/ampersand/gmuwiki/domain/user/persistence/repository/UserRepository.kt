package team.ampersand.gmuwiki.domain.user.persistence.repository

import org.springframework.data.repository.CrudRepository
import team.ampersand.gmuwiki.domain.user.persistence.persist.UserEntity
import java.util.UUID

interface UserRepository : CrudRepository<UserEntity, UUID> {
}