package com.skc.app.droid.x

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.parent() =
    this
        .fillMaxSize()
        .padding(horizontal = 15.dp)