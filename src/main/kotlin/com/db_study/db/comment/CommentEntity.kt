package com.db_study.db.comment

import com.db_study.core.comment.Comment
import com.db_study.db.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "comments")
class CommentEntity(
    val postId: Long,
    val content: String,
) : BaseEntity() {

    fun toComment(): Comment {
        return Comment(
            id = this.id!!,
            content = this.content,
        )
    }
}