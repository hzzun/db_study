package com.db_study.db.comment

import com.db_study.core.comment.Comment
import com.db_study.core.comment.CommentRepository
import org.springframework.stereotype.Repository

@Repository
class CommentEntityRepository(
    private val commentJpaRepository: CommentJpaRepository,
) : CommentRepository {
    override fun save(postId: Long, content: String): Comment {
        return commentJpaRepository.save(CommentEntity(postId, content)).toComment()
    }
}