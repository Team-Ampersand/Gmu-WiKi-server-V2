package team.ampersand.gmuwiki.domain.user.persistence

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.ampersand.gmuwiki.domain.user.model.User
import team.ampersand.gmuwiki.domain.user.persistence.mapper.UserMapper
import team.ampersand.gmuwiki.domain.user.persistence.repository.UserRepository
import team.ampersand.gmuwiki.domain.user.spi.UserSpi
import java.util.*

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserSpi {

    override fun save(user: User): User =
        userMapper.toEntity(user)
            .let { userRepository.save(it) }
            .let { userMapper.toDomain(it)!! }

    override fun delete(user: User) =
        userMapper.toEntity(user)
            .let { userRepository.delete(it) }

    override fun queryUserById(id: UUID): User? =
        userRepository.findByIdOrNull(id)
            .let { userMapper.toDomain(it) }

    override fun queryUserByEmail(email: String): User? =
        userRepository.findByEmail(email)
            .let { userMapper.toDomain(it) }
}