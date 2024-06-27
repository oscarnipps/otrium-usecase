package com.android.otrium.data.repo

import com.android.otrium.data.DateUtils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalEntityMapperTest{

    private lateinit var localEntityMapper: LocalEntityMapper

    @Before
    fun setUp() {
        localEntityMapper = LocalEntityMapper(DateUtils())
    }

    @Test
    fun `test expired cache date returns true`() {
        val currentDateTime = "2024-06-26 10:30:00"

        localEntityMapper = LocalEntityMapper(DateUtils())

        val expected = localEntityMapper.mapToHasCacheExceeded1Day(currentDateTime)

        assertTrue(expected)
    }

    @Test
    fun `test valid cache date returns false`() {
        val formatter = DateTimeFormatter.ofPattern(DateUtils.TIME_FORMAT_PATTERN)

        val currentDateTime = LocalDateTime.now().format(formatter)

        val expected = localEntityMapper.mapToHasCacheExceeded1Day(currentDateTime)

        assertFalse(expected)
    }
}