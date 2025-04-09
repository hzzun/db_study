package com.db_study.service

import com.db_study.entity.Meeting
import com.db_study.repository.MeetingRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.concurrent.TimeUnit

@Service
class RedisMeetingService(
    private val meetingRepository: MeetingRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun register(date: LocalDate, seats: Int): Long {
        val meeting = meetingRepository.save(Meeting(date = date, seats = seats))

        // Redis 에 좌석 정보 캐싱
        val key = "meeting-seats on [$date]"
        redisTemplate.opsForValue().set(key, seats)

        return meeting.id!!
    }

    @Transactional
    fun reserve(date: LocalDate): Boolean {
        val key = "meeting-seats on [$date]"
        val lockKey = "meeting-lock on [$date]"
        val cacheTimeout = 24 * 60 * 60L

        try {
            val lockAcquired = redisTemplate.opsForValue()
                .setIfAbsent(lockKey, "locked", 1, TimeUnit.SECONDS)

            if (lockAcquired != true) {
                throw Exception("락 획득 실패")
            }

            var cachedSeats = redisTemplate.opsForValue().get(key)?.toString()?.toIntOrNull() ?: -1

            if (cachedSeats >= 0) {
                if (cachedSeats > 0) {
                    redisTemplate.opsForValue().decrement(key)
                    val meeting = meetingRepository.findByDateWithPessimisticLock(date)
                        ?: throw Exception("$date 일자에는 미팅이 등록되어 있지 않습니다.")
                    if (meeting.seats > 0) {
                        meeting.seats -= 1
                    } else {
                        throw Exception("$date 일자 좌석이 모두 소진되었습니다.")
                    }
                } else {
                    throw Exception("$date 일자 좌석이 모두 소진되었습니다.")
                }
            } else {
                val meeting = meetingRepository.findByDateWithPessimisticLock(date)
                    ?: throw Exception("$date 일자에는 미팅이 등록되어 있지 않습니다.")

                if (meeting.seats <= 0) {
                    throw Exception("$date 일자 좌석이 모두 소진되었습니다.")
                }

                meeting.seats -= 1
                meetingRepository.save(meeting)
                redisTemplate.opsForValue().set(key, meeting.seats, cacheTimeout, TimeUnit.SECONDS)
            }

            return true
        } catch (e: Exception) {
            throw Exception(e.message, e)
        } finally {
            redisTemplate.delete(lockKey)
        }
    }
}