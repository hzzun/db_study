package com.db_study.service

import com.db_study.entity.Meeting
import com.db_study.repository.MeetingRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MeetingService(
    private val meetingRepository: MeetingRepository
) {
    fun register(date: LocalDate, seats: Int): Long {
        return meetingRepository.save(Meeting(date = date, seats = seats)).id!!
    }
}