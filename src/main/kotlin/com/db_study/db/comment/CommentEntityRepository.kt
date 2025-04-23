package com.db_study.db.comment

import com.db_study.core.comment.Comment
import com.db_study.core.comment.CommentRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.LocalDateTime

@Repository
class CommentEntityRepository(
    private val commentJpaRepository: CommentJpaRepository,
    private val jdbcTemplate: JdbcTemplate
) : CommentRepository {
    override fun findByPostId(postId: Long): List<Comment> {
        return commentJpaRepository.findByPostId(postId).map {
            it.toComment()
        }.toList()
    }

    override fun save(postId: Long, content: String): Comment {
        return commentJpaRepository.save(CommentEntity(postId, content)).toComment()
    }

    override fun saveAll(postId: Long, comments: Iterable<Comment>) {
        val sql = "INSERT INTO comments (created_at, modified_at, post_id, content) VALUES (?, ?, ?, ?)"

        val target = comments.map {
            CommentEntity(postId, it.content)
        }

        jdbcTemplate.batchUpdate(sql, target, 1000) { ps, commentEntity ->
            ps.setObject(1, LocalDateTime.now())
            ps.setObject(2, LocalDateTime.now())
            ps.setLong(3, commentEntity.postId)
            ps.setString(4, commentEntity.content)
        }
    }
}