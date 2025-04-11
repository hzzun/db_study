package com.db_study.core.topic

import com.db_study.core.comment.Comment
import com.db_study.core.comment.CommentRepository
import com.db_study.core.post.PostRepository
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
) {
    fun getTopic(id: Long): Topic {
        val post = postRepository.findById(id)
            ?: throw IllegalArgumentException("Post with ID $id not found")
        val comments: List<Comment> = commentRepository.findByPostId(id)

        return Topic(
            id = post.id,
            post = post,
            comments = comments
        )
    }

}
