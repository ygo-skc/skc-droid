package com.skc.app.droid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Events(
    val service: String,
    val events: List<Event>
) : Parcelable

@Parcelize
data class Event(
    val name: String,
    val notes: String,
    val location: String,
    val eventDate: String,
    val url: String,
) : Parcelable