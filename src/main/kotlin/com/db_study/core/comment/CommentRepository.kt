package com.db_study.core.comment

interface CommentRepository {

    fun findByPostId(postId: Long): List<Comment>

    fun save(postId: Long, content: String): Comment
}