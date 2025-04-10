package com.db_study.core.comment

import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun append(postId: Long, content: String): Long {
        val savedComment = commentRepository.save(postId, content)
        return savedComment.id
    }
}