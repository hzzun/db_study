package com.db_study.core.comment

interface CommentRepository {

    fun save(postId: Long, content: String): Comment
}