package com.shdwraze.notgoing.ui.component

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shdwraze.notgoing.network.model.entity.Lesson
import com.shdwraze.notgoing.ui.theme.ImNotGoingTheme
import java.util.Date

@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
    lesson: Lesson
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp),
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
                .heightIn(min = 120.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
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
                    text = lesson.subjectFull,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = lesson.teacher ?: "Н/д",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = lesson.subjectBrief,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun convertUnixToTime(unixTimestamp: Long): String {
    val sdf = SimpleDateFormat("HH:mm")
    val date = Date(unixTimestamp * 1000)
    return sdf.format(date)
}

val testLesson = Lesson(
    startTime = 1635680400000, // 31 октября 2023 года, 13:00 по GMT
    endTime = 1635684000000, // 31 октября 2023 года, 14:00 по GMT
    type = "Лк",
    teacher = "Іванов І.І.",
    subjectBrief = "ОПК",
    subjectFull = "Основи програмування Kotlin"
)

@Preview(showBackground = true)
@Composable
fun LessonCardPreview() {
    ImNotGoingTheme {
        LessonCard(lesson = testLesson)
    }
}