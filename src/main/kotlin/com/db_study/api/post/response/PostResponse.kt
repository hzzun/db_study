package com.db_study.api.post.response

import com.db_study.core.post.Post

data class PostResponse(
    val id: Long,
    val content: String,
) {
    companion object {
        fun toResponse(post: Post): PostResponse {
            return PostResponse(
                id = post.id,
                content = post.content
            )
        }
    }
}
