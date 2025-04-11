package com.db_study.api.comment.response

import com.db_study.core.comment.Comment

data class CommentResponse(
    val id: Long,
    val content: String,
) {
    companion object {
        fun toResponse(comment: Comment): CommentResponse {
            return CommentResponse(
                id = comment.id,
                content = comment.content
            )
        }
    }
}
