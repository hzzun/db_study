package com.db_study.db.post

import com.db_study.core.post.Post
import com.db_study.db.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "posts")
class PostEntity(
    val content: String,
) : BaseEntity() {

    fun toPost(): Post {
        return Post(
            id = this.id!!,
            content = content
        )
    }
}