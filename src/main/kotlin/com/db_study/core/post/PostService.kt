package com.db_study.core.post

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class PostService(
    private val postRepository: PostRepository,
    private val redisTemplate: RedisTemplate<String, Any>,
    private val objectMapper: ObjectMapper
) {
    fun getSinglePost(id: Long): Post {
        // 캐싱된 데이터가 있는지 조회
        val cachedPostContent = redisTemplate.opsForValue().get("post:$id") as String?

        // 캐싱된 데이터가 있다면 그대로 리턴
        if (cachedPostContent != null) {
            return Post(id, cachedPostContent)
        }

        // 캐싱된 데이터가 없으면 데이터베이스에서 조회
        val postFromDb = postRepository.findById(id) ?: throw Exception("Post with ID $id not found")

        // 다시 Redis 에 저장 (캐싱)
        redisTemplate.opsForValue().set(id.toString(), postFromDb, 60, TimeUnit.SECONDS)

        return postFromDb
    }

    fun getSinglePostWithoutRedis(id: Long): Post {
        val postFromDb = postRepository.findById(id) ?: throw Exception("Post with ID $id not found")

        return postFromDb
    }

    fun append(content: String): Long {
        // 데이터베이스 저장
        val savedPost = postRepository.save(content)
        val savedPostId = savedPost.id

        // Redis 저장
        redisTemplate.opsForValue().set(savedPostId.toString(), savedPost, 300, TimeUnit.SECONDS)

        return savedPostId
    }

    fun appendOnlyContentToRedis(content: String): Long {
        val savedPost = postRepository.save(content)
        val savedPostId = savedPost.id

        redisTemplate.opsForValue().set("post:$savedPostId", content, 300, TimeUnit.SECONDS)

        return savedPostId
    }
}