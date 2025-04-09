package com.db_study.core.post

import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun append(content: String): Long {
        return postRepository.save(content)
    }
}