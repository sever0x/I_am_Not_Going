package com.shdwraze.app.ui.component

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shdwraze.app.network.Lesson
import java.util.Date

@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
    lesson: Lesson
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(start = 8.dp, top = 16.dp, end = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (lesson.type) {
                "Лк" -> MaterialTheme.colorScheme.primaryContainer
                "Лб" -> MaterialTheme.colorScheme.secondaryContainer
                else -> MaterialTheme.colorScheme.tertiaryContainer
            }
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "${convertUnixToTime(lesson.startTime)} - ${convertUnixToTime(lesson.endTime)}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = lesson.type,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = lesson.subject.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = lesson.teachers[0].shortName,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = lesson.subject.brief,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun convertUnixToTime(unixTimestamp: Long): String {
    val sdf = SimpleDateFormat("HH:mm")
    val date = Date(unixTimestamp * 1000)
    return sdf.format(date)
}