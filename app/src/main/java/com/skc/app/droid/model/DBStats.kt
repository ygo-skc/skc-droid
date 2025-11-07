package com.skc.app.droid.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DBStats(
    val productTotal: Int,
    val cardTotal: Int,
    val banListTotal: Int,
    val yearsOfBanListCoverage: Int
) : Parcelable
