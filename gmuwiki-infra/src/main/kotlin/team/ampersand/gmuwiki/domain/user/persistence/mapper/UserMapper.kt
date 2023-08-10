package team.ampersand.gmuwiki.domain.user.persistence.mapper

import team.ampersand.gmuwiki.domain.user.model.User
import team.ampersand.gmuwiki.domain.user.persistence.persist.UserEntity
import team.ampersand.gmuwiki.global.mapper.GenericMapper

class UserMapper : GenericMapper<User, UserEntity>{

    override fun toDomain(entity: UserEntity?): User? {
        return entity?.let {
            User(
                id = it.id,
                email = it.email,
                name = it.name,
                grade = it.grade,
                classNum = it.classNum,
                number = it.number,
                profileImageUrl = it.profileImageUrl,
                authority = it.authority
            )
        }
    }

    override fun toEntity(domain: User): UserEntity =
        UserEntity(
            id = domain.id,
            email = domain.email,
            name = domain.name,
            grade = domain.grade,
            classNum = domain.classNum,
            number = domain.number,
            profileImageUrl = domain.profileImageUrl,
            authority = domain.authority
        )
}