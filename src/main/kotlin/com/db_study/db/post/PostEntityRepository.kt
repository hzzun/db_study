package com.db_study.db.post

import com.db_study.core.post.Post
import com.db_study.core.post.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PostEntityRepository(
    private val postJpaRepository: PostJpaRepository
) : PostRepository {
    override fun findById(id: Long): Post? {
        return postJpaRepository.findByIdOrNull(id)?.toPost()
    }

    override fun save(content: String): Post {
        val savedPost = postJpaRepository.save(PostEntity(content))
        return savedPost.toPost()
    }
}