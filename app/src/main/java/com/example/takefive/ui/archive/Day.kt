package com.example.takefive.ui.archive

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.takefive.data.EntryType
import com.example.takefive.ui.theme.T5_DARK_BLUE
import com.example.takefive.ui.theme.T5_LIGHT_BLUE
import com.example.takefive.ui.theme.T5_RED
import com.example.takefive.ui.theme.T5_WHITE
import com.example.takefive.ui.theme.TheJournalTheme
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate

/**
 * An individual day on the calendar
 */
@Composable
fun Day(
    day: CalendarDay,
    isSelectable: Boolean,
    isToday: Boolean,
    isMorningCompleted: Boolean,
    isEveningCompleted: Boolean,
    onDateClick: (LocalDate, EntryType) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(1.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.85f)
                .background(if (isToday) T5_RED else Color.Transparent)
                // TODO: Figure out how to handle which entry type to show after clicking on a date
                .clickable(onClick = { onDateClick(day.date, EntryType.EVENING) }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = day.date.dayOfMonth.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = if (isToday) T5_WHITE else if (isSelectable) Color.Black else Color.Gray
            )
            DayCompletionIndicator(
                isMorningCompleted,
                isEveningCompleted,
                Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun DayCompletionIndicator(
    morningCompleted: Boolean,
    eveningCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    if (morningCompleted || eveningCompleted) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(6.dp)
        ) {
            if (morningCompleted && eveningCompleted) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(T5_LIGHT_BLUE)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(T5_DARK_BLUE)
                )
            } else if (morningCompleted) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(T5_LIGHT_BLUE)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(T5_DARK_BLUE)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayPreview_Today() {
    TheJournalTheme {
        Day(
            day = CalendarDay(date = LocalDate.now(), position = DayPosition.MonthDate),
            isSelectable = true,
            isToday = true,
            isMorningCompleted = true,
            isEveningCompleted = true,
            onDateClick = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DayPreview_NotToday_Completed() {
    TheJournalTheme {
        Day(
            day = CalendarDay(date = LocalDate.now(), position = DayPosition.MonthDate),
            isSelectable = true,
            isToday = false,
            isMorningCompleted = true,
            isEveningCompleted = true,
            onDateClick = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DayPreview_NotSelectable() {
    TheJournalTheme {
        Day(
            day = CalendarDay(date = LocalDate.now(), position = DayPosition.MonthDate),
            isSelectable = false,
            isToday = false,
            isMorningCompleted = false,
            isEveningCompleted = false,
            onDateClick = { _, _ -> }
        )
    }
}
