package com.db_study.core.post

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class PostService(
    private val postRepository: PostRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) {



    fun append(content: String): Long {
        // 데이터베이스 저장
        val savedPost = postRepository.save(content)
        val savedPostId = savedPost.id

        // Redis 저장
        redisTemplate.opsForValue().set(savedPostId.toString(), savedPost, 300, TimeUnit.SECONDS)

        return savedPostId
    }
}