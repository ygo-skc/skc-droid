package com.skc.app.droid.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skc.app.droid.model.Events
import com.skc.app.droid.ui.utility.DateBadge
import com.skc.app.droid.util.DatesUtil
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun UpcomingTCGProducts(
    upcomingYGOProducts: Events,
    onCardLinkClick: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(
                text = "Upcoming products",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "TCG products that have been announced by Konami and of which we know the tentative date of.",
            )
        }

        Column {
            upcomingYGOProducts.events.forEach { event ->
                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                    DateBadge(
                        date = event.eventDate,
                        formatter = DatesUtil.HEART_API_DATE_FORMAT
                    )
                    Column {
                        Text(text = event.name, fontWeight = FontWeight.Bold)
                        MarkdownText(
                            markdown = event.notes,
                            linkColor = MaterialTheme.colorScheme.secondary,
                            onLinkClicked = onCardLinkClick
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}