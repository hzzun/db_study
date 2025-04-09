package com.db_study.core.post

interface PostRepository {

    fun save(content: String): Post
}