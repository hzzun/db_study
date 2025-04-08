package com.db_study.controller

import com.db_study.controller.request.MeetingAppendRequest
import com.db_study.service.MeetingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class MeetingController(
    private val meetingService: MeetingService
) {
    @PostMapping("/api/meetings")
    fun registerMeeting(@RequestBody request: MeetingAppendRequest): ResponseEntity<Long> {
        val result = meetingService.register(request.date, request.seats)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/api/meetings/reserve")
    fun reserveMeeting(@RequestParam date: LocalDate): ResponseEntity<Boolean> {
        val result = meetingService.reserve(date)
        return ResponseEntity.ok(result)
    }
}