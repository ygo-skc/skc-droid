package com.skc.app.droid.x

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

fun LocalDateTime.isDateInvalidated(
    amount: Long = 10,
    unit: TemporalUnit = ChronoUnit.MINUTES
): Boolean {
    return this.isBefore(LocalDateTime.now().minus(amount, unit))
}