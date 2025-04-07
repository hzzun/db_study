package com.db_study.controller.request

import java.time.LocalDate

data class MeetingAppendRequest(
    val date: LocalDate,
    val seats: Int,
)
