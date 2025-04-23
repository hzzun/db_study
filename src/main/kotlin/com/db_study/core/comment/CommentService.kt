package com.db_study.core.comment

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    @Transactional
    fun appendBulk(postId: Long, content: String) {
        val comments = mutableListOf<Comment>()

        for (i in 1..10) {
            comments.add(Comment(i.toLong(), "${i}th $content"))
        }
        commentRepository.saveAll(postId, comments)
    }
}