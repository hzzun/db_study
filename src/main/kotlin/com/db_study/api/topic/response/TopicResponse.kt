package com.db_study.api.topic.response

import com.db_study.api.comment.response.CommentResponse
import com.db_study.api.post.response.PostResponse
import com.db_study.core.topic.Topic

data class TopicResponse(
    val id: Long,
    val post: PostResponse,
    val comments: List<CommentResponse>
) {
    companion object {
        fun toResponse(topic: Topic): TopicResponse {
            return TopicResponse(
                id = topic.id,
                post = PostResponse.toResponse(topic.post),
                comments = topic.comments.map { CommentResponse.toResponse(it) }
            )
        }
    }
}
