package com.skc.app.droid.x

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import kotlin.math.abs

fun LocalDateTime.isDateInvalidated(
    amount: Long = 10,
    unit: TemporalUnit = ChronoUnit.MINUTES
): Boolean = this.isBefore(LocalDateTime.now().minus(amount, unit))

fun LocalDate.isFutureDate() = this.isAfter(LocalDate.now())

fun LocalDate.daysSinceToday() = abs(this.until(LocalDate.now(), ChronoUnit.DAYS))