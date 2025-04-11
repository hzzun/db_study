package com.db_study.api.topic

import com.db_study.api.topic.response.TopicResponse
import com.db_study.core.topic.Topic
import com.db_study.core.topic.TopicService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TopicController(
    private val topicService: TopicService
) {
    @GetMapping("/topic/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<TopicResponse> {
        val result: Topic = topicService.getTopic(id)
        return ResponseEntity.ok(TopicResponse.toResponse(result))
    }
}