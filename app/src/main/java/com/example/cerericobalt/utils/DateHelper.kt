package com.example.cerericobalt.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashSet

object DateHelper {
    private val HOLIDAYS = Collections.unmodifiableSet(
        HashSet(
            listOf<LocalDate>(
                LocalDate.of(2020, 11, 30),
                LocalDate.of(2020, 12, 1),
                LocalDate.of(2020, 12, 25),
                LocalDate.of(2020, 12, 26),
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2),
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2021, 4, 30),
                LocalDate.of(2021, 5, 1),
                LocalDate.of(2021, 5, 2),
                LocalDate.of(2021, 5, 3),
                LocalDate.of(2021, 6, 1),
                LocalDate.of(2021, 6, 21),
                LocalDate.of(2021, 8, 15),
                LocalDate.of(2021, 11, 30),
                LocalDate.of(2021, 12, 1),
                LocalDate.of(2021, 12, 25),
                LocalDate.of(2021, 12, 26)
            )
        )
    )

    fun getBusinessDays(startDate: String, endDate: String): String {
        val f: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val startInclusive = LocalDate.parse(startDate.replace(". ", "/"), f)
        val endExclusive = LocalDate.parse(endDate.replace(". ", "/"), f)

        var businessDays = 0
        var d = startInclusive
        while (d.isBefore(endExclusive)) {
            val dw: DayOfWeek = d.dayOfWeek
            if (!HOLIDAYS.contains(d) && dw !== DayOfWeek.SATURDAY && dw !== DayOfWeek.SUNDAY) {
                businessDays++
            }
            d = d.plusDays(1)
        }
        return businessDays.toString()
    }
}