package com.db_study.core.post

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class PostService(
    private val postRepository: PostRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun append(content: String): Long {
        // Redis 저장
        val key = UUID.randomUUID().toString()
        redisTemplate.opsForValue().set(key, content, 300, TimeUnit.SECONDS)

        // 데이터베이스 저장
        return postRepository.save(content)
    }
}