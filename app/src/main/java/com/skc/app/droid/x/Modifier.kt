package com.skc.app.droid.x

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.parent(innerPadding: PaddingValues) =
    this
        .fillMaxSize()
        .padding(paddingValues = innerPadding)
        .padding(horizontal = 15.dp)