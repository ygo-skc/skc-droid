package com.skc.app.droid.util

import java.time.format.DateTimeFormatter

class DatesUtil {
    companion object {
        val SKC_DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}
