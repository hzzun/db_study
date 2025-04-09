package com.db_study.api.post

import com.db_study.api.post.request.PostAppendRequest
import com.db_study.api.post.response.PostResponse
import com.db_study.core.post.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService
) {
    @GetMapping("/posts/{id}")
    fun getSinglePost(@PathVariable id: Long): ResponseEntity<PostResponse> {
        val result = postService.getSinglePost(id)
        return ResponseEntity.ok(PostResponse.toResponse(result))
    }

    @PostMapping("/posts")
    fun append(@RequestBody request: PostAppendRequest): ResponseEntity<Long> {
        val result = postService.append(request.content)
        return ResponseEntity.ok(result)
    }
}