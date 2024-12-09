package com.example.thejournal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thejournal.ui.theme.TheJournalTheme
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.nextMonth
import com.kizitonwose.calendar.core.previousMonth
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

/**
 * Calendar that shows completed entries and one can select a previous date to read entries from
 */
@Composable
fun Calendar(
    completedDates: List<LocalDate>?,
    onDateClick: (LocalDate) -> Unit,
    onCalendarNavigationClick: (YearMonth) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    val today = remember { LocalDate.now() }
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(120) }
    val endMonth = remember { currentMonth.plusMonths(120) }
    val daysOfWeek = remember { daysOfWeek() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first(),
        outDateStyle = OutDateStyle.EndOfRow,
    )
    val visibleMonth = rememberFirstCompletelyVisibleMonth(state)

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        CalendarTitle(
            currentMonth = visibleMonth.yearMonth,
            goToPrevious = {
                val previousMonth = state.firstVisibleMonth.yearMonth.previousMonth
                coroutineScope.launch {
                    state.animateScrollToMonth(previousMonth)
                    onCalendarNavigationClick(previousMonth)
                }
            },
            goToNext = {
                val nextMonth = state.firstVisibleMonth.yearMonth.nextMonth
                coroutineScope.launch {
                    state.animateScrollToMonth(nextMonth)
                    onCalendarNavigationClick(nextMonth)
                }
            },
        )

        HorizontalCalendar(
            modifier = Modifier.wrapContentWidth(),
            state = state,
            dayContent = { day ->
                val isSelectable = day.position == DayPosition.MonthDate
                val isCompleted = completedDates?.contains(day.date) ?: false
                val isToday = day.position == DayPosition.MonthDate && day.date == today
                Day(day, isSelectable, isToday, isCompleted, onDateClick)
            },
            monthHeader = {
                DaysOfWeekHeader(
                    modifier = Modifier.padding(vertical = 8.dp),
                    daysOfWeek = daysOfWeek,
                )
            },
        )
    }
}

/**
 * Heading row to display days of the week above the calendar
 */
@Composable
private fun DaysOfWeekHeader(
    modifier: Modifier = Modifier,
    daysOfWeek: List<DayOfWeek> = emptyList(),
) {
    Row(modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.Black,
                text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.US),
                fontWeight = FontWeight.Light
            )
        }
    }
}

/**
 * Finds the first fully visible month in the layout.
 */
@Composable
private fun rememberFirstCompletelyVisibleMonth(state: CalendarState): CalendarMonth {
    val visibleMonth = remember(state) { mutableStateOf(state.firstVisibleMonth) }
    // Only take non-null values as null will be produced when the
    // list is mid-scroll as no index will be completely visible.
    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.visibleMonthsInfo.firstOrNull() }
            .filterNotNull()
            .collect { info -> visibleMonth.value = info.month }
    }
    return visibleMonth.value
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7A)
@Composable
fun CalendarScreenPreview() {
    TheJournalTheme {
        Calendar(
            completedDates = emptyList(),
            onDateClick = {},
            onCalendarNavigationClick = {}
        )
    }
}
