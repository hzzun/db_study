package com.db_study.api.post

import com.db_study.api.post.request.PostAppendRequest
import com.db_study.core.post.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService
) {
    @PostMapping("/posts")
    fun append(@RequestBody request: PostAppendRequest): ResponseEntity<Long> {
        val result = postService.append(request.content)
        return ResponseEntity.ok(result)
    }
}