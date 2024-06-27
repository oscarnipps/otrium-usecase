package com.android.otrium.data

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateUtils @Inject constructor (){

    companion object{
        const val TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss"
        const val CACHE_DURATION_PERIOD_IN_HOURS = 24
    }

    fun getCurrentLocalTime(): String{
        val currentDateTime = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN)

        return  currentDateTime.format(formatter)
    }

    fun hasExceeded24Hours(dateTimeString: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN)

        val parsedDateTime = LocalDateTime.parse(dateTimeString, formatter)

        val currentDateTime = LocalDateTime.now()

        val duration = Duration.between(parsedDateTime, currentDateTime)

        return duration.toHours() > CACHE_DURATION_PERIOD_IN_HOURS
    }

}