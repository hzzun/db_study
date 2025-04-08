package com.db_study.service

import com.db_study.entity.Meeting
import com.db_study.repository.MeetingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MeetingService(
    private val meetingRepository: MeetingRepository
) {
    fun register(date: LocalDate, seats: Int): Long {
        return meetingRepository.save(Meeting(date = date, seats = seats)).id!!
    }

    @Transactional
    fun reserve(date: LocalDate): Boolean {
        try {
            // 선택한 날짜에 대한 미팅을 조회
            val findMeeting = meetingRepository.findByDateWithPessimisticLock(date)
                ?: throw Exception("$date 일자에는 미팅이 등록되어 있지 않습니다.")

            // 미팅 좌석 수 -1
            if (findMeeting.seats > 0) {
                findMeeting.seats -= 1
            } else {
                throw Exception("$date 일자 좌석이 모두 소진되었습니다.")
            }

        } catch (e: Exception) {
            throw Exception(e.message, e)
        }

        return true
    }
}