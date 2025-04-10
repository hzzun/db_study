package com.db_study.api.comment

import com.db_study.api.comment.request.CommentAppendRequest
import com.db_study.core.comment.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService
) {
    @PostMapping("/comments/{postId}")
    fun append(@PathVariable postId: Long, @RequestBody request: CommentAppendRequest): ResponseEntity<Long> {
        val result = commentService.append(postId, request.content)
        return ResponseEntity.ok(result)
    }
}