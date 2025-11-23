package com.skc.app.droid.x

import java.text.DecimalFormat

private val formatter = DecimalFormat("#,###")

fun Long.format() = formatter.format(this)