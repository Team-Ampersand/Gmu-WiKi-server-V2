package team.ampersand.gmuwiki.domain.user.persistence.persist

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import team.ampersand.gmuwiki.domain.auth.model.constant.Authority
import team.ampersand.gmuwiki.global.entity.BaseUUIDEntity
import java.util.*

@Entity
@Table(name = "tbl_user")
class UserEntity(

    override val id: UUID,

    @Column(columnDefinition = "VARCHAR(30)", nullable = false, unique = true)
    val email: String,

    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    val name: String,

    @Column(nullable = true)
    val grade: Int?,

    @Column(nullable = true)
    val classNum: Int?,

    @Column(nullable = true)
    val number: Int?,

    @Column(columnDefinition = "TEXT", nullable = false)
    var profileImageUrl: String,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(7)", nullable = false)
    val authority: Authority,

    ) : BaseUUIDEntity(id)