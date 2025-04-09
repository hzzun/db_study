package com.db_study.core.post

interface PostRepository {

    fun findById(id: Long): Post?

    fun save(content: String): Post
}