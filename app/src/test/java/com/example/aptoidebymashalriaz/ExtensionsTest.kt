package com.example.aptoidebymashalriaz

import com.example.aptoidebymashalriaz.ui.utils.getFormattedDownloads
import com.example.aptoidebymashalriaz.ui.utils.getFormattedRating
import com.example.aptoidebymashalriaz.ui.utils.getFormattedSize
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Tests for the [Int?.getFormattedDownloads] extension.
 * GIVEN an integer count (or null)
 * WHEN getFormattedDownloads() is called
 * THEN it returns the expected abbreviated string
 */
@RunWith(Parameterized::class)
class GetFormattedDownloadsTest(
    private val input: Int?,
    private val expected: String
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "input: {0} => expected: {1}")
        fun data(): Collection<Array<Any?>> = listOf(
            arrayOf(null, ""),
            arrayOf(532, "532"),
            arrayOf(1000, "1K"),
            arrayOf(1200, "1.2K"),
            arrayOf(2000, "2K"),
            arrayOf(1234567, "1.2M")
        )
    }

    @Test
    fun `GIVEN integer count WHEN getFormattedDownloads is called THEN returns expected abbreviation`() {
        val result = input.getFormattedDownloads()
        assertEquals(expected, result)
    }
}

/**
 * Tests for the [Double?.getFormattedRating] extension.
 * GIVEN a nullable rating value
 * WHEN getFormattedRating() is called
 * THEN it returns the rating as a numeric string if > 0, otherwise a placeholder (“--”)
 */
@RunWith(Parameterized::class)
class GetFormattedRatingTest(
    private val input: Double?,
    private val expected: String
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "input: {0} => expected: {1}")
        fun data(): Collection<Array<Any?>> = listOf(
            arrayOf(null, "--"),
            arrayOf(0.0, "--"),
            arrayOf(4.5, "4.5"),
            arrayOf(5.0, "5.0")
        )
    }

    @Test
    fun `GIVEN rating value WHEN getFormattedRating is called THEN returns expected formatted rating`() {
        val result = input.getFormattedRating()
        assertEquals(expected, result)
    }
}

/**
 * Tests for the [Long?.getFormattedSize] extension.
 * GIVEN a nullable byte count
 * WHEN getFormattedSize() is called
 * THEN it returns a human‑readable file size string
 */
@RunWith(Parameterized::class)
class GetFormattedSizeTest(
    private val input: Long?,
    private val expected: String
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "input: {0} => expected: {1}")
        fun data(): Collection<Array<Any?>> = listOf(
            arrayOf(null, ""),
            arrayOf(512L, "512 B"),
            arrayOf(1024L, "1 KB"),
            arrayOf(1536L, "1.5 KB"),
            arrayOf(1048576L, "1 MB")
        )
    }

    @Test
    fun `GIVEN byte count WHEN getFormattedSize is called THEN returns expected human readable size`() {
        val result = input.getFormattedSize()
        assertEquals(expected, result)
    }
}