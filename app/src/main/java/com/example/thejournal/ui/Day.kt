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
import com.example.thejournal.data.EntryType
import com.example.thejournal.ui.theme.T5_DARK_BLUE
import com.example.thejournal.ui.theme.T5_LIGHT_BLUE
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
    isMorningCompleted: Boolean,
    isEveningCompleted: Boolean,
    onDateClick: (LocalDate, EntryType) -> Unit,
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
            // TODO: Figure out how to handle which entry type to show after clicking on a date
            .clickable(onClick = { onDateClick(day.date, EntryType.EVENING) })
    ) {
        Box(
            modifier = modifier
                .aspectRatio(1f)
                .padding(6.dp)
                .border(
                    width = if (isEveningCompleted) 4.dp else 0.dp,
                    color = if (isEveningCompleted) T5_DARK_BLUE else Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Box(
                modifier = modifier
                    .aspectRatio(1f)
                    .padding(6.dp)
                    .border(
                        width = if (isMorningCompleted) 4.dp else 0.dp,
                        color = if (isMorningCompleted) T5_LIGHT_BLUE else Color.Transparent,
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
