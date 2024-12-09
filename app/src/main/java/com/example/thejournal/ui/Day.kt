package com.example.thejournal.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thejournal.ui.theme.T5_DARK
import com.example.thejournal.ui.theme.T5_RED
import com.example.thejournal.ui.theme.TheJournalTheme
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
    isCompleted: Boolean,
    onDateClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(1.dp)
            .border(
                width = if (isToday) 4.dp else 0.dp,
                color = if (isToday) T5_RED else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = { onDateClick(day.date) })
    ) {
        Box(
            modifier = modifier
                .aspectRatio(1f)
                .padding(8.dp)
                .border(
                    width = if (isCompleted) 4.dp else 0.dp,
                    color = if (isCompleted) T5_DARK else Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = day.date.dayOfMonth.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelectable) Color.Black else Color.Gray
            )
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
            isCompleted = true,
            onDateClick = {}
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
            isCompleted = true,
            onDateClick = {}
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
            isCompleted = false,
            onDateClick = {}
        )
    }
}
