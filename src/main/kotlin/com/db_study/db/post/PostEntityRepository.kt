package com.db_study.db.post

import com.db_study.core.post.PostRepository
import org.springframework.stereotype.Repository

@Repository
class PostEntityRepository(
    private val postJpaRepository: PostJpaRepository
) : PostRepository {

    override fun save(content: String): Long {
        return postJpaRepository.save(PostEntity(content)).id!!
    }
}