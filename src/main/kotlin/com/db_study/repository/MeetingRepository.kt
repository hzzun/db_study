package com.db_study.repository

import com.db_study.entity.Meeting
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface MeetingRepository : JpaRepository<Meeting, Long> {

    fun findByDate(date: LocalDate): Meeting?

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM Meeting m WHERE m.date = :date")
    fun findByDateWithPessimisticLock(date: LocalDate): Meeting?
}