package com.skc.app.droid.model

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.gson.annotations.SerializedName
import com.skc.app.droid.R

enum class YGOCardImageVariant() {
    ROUND,
    ROUNDED_CORNER,
}

enum class YGOAttribute(val value: String, val drawable: Int) {
    @SerializedName("Dark")
    DARK(value = "Dark", drawable = R.drawable.dark_attribute),

    @SerializedName("Light")
    LIGHT(value = "Light", drawable = R.drawable.light_attribute),

    @SerializedName("Wind")
    WIND(value = "Wind", drawable = R.drawable.wind_attribute),

    @SerializedName("Earth")
    EARTH(value = "Earth", drawable = R.drawable.earth_attribute),

    @SerializedName("Water")
    WATER(value = "Water", drawable = R.drawable.water_attribute),

    @SerializedName("Fire")
    FIRE(value = "Fire", drawable = R.drawable.fire_attribute),

    @SerializedName("Divine")
    DIVINE(value = "Divine", drawable = R.drawable.divine_attribute),

    @SerializedName("Spell")
    SPELL(value = "Spell", drawable = R.drawable.spell_attribute),

    @SerializedName("Trap")
    TRAP(value = "Trap", drawable = R.drawable.trap_attribute),

    @SerializedName("Unknown")
    UNKNOWN(value = "Unknown", drawable = 0),
}

enum class YGOImageSize(val value: String) {
    TINY("tn"),
    X_SMALL("x-sm"),
    SMALL("sm"),
    MEDIUM("md"),
    LARGE("lg"),
    ORIGINAL("original")
}

enum class TaskStatus() {
    PENDING,
    DONE,
    UNINITIATED,
}

enum class IconModifier(val modifier: Modifier) {
    SMALL(modifier = Modifier.size(16.dp)),
    REGULAR(
        modifier = Modifier
            .size(23.dp)
            .clip(CircleShape)
    ),
    LARGE(modifier = Modifier.size(30.dp)),
}