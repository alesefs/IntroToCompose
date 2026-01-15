package com.example.introtocompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroToComposeTheme {
                CalendarP2Screen()
            }
        }
    }
}

@Composable
fun CalendarP2Screen() {

    var showDialog by remember { mutableStateOf(false) }
    var selectedRange by remember {
        mutableStateOf<Pair<Calendar?, Calendar?>>(null to null)
    }

    val formatter = remember {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    }

    val textRange = remember(selectedRange) {
        val start = selectedRange.first
        val end = selectedRange.second

        if (start != null && end != null) {
            "${formatter.format(start.time)} - ${formatter.format(end.time)}"
        } else {
            ""
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Selecionar data")
            }

            Text(
                text = textRange,
                fontSize = 16.sp
            )
        }
    }

    if (showDialog) {
        CalendarRangeDialog(
            onDismiss = { showDialog = false },
            onConfirm = { start, end ->
                selectedRange = start to end
                showDialog = false
            }
        )
    }
}

@Composable
fun MonthCalendar(
    year: Int,
    month: Int,
    startDate: Calendar?,
    endDate: Calendar?,
    onDayClick: (Calendar) -> Unit
) {
    val days = remember(year, month) {
        generateMonthDays(year, month)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(days) { day ->
            DayCell(
                day = day,
                startDate = startDate,
                endDate = endDate,
                onClick = onDayClick
            )
        }
    }
}

fun sameDay(c1: Calendar, c2: Calendar): Boolean {
    return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
            c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)
}

fun isBetween(date: Calendar, start: Calendar, end: Calendar): Boolean {
    return date.after(start) && date.before(end)
}

@Composable
fun DayCell(
    day: CalendarDay,
    startDate: Calendar?,
    endDate: Calendar?,
    onClick: (Calendar) -> Unit
) {

    val isSelected =
        startDate != null && endDate == null &&
                sameDay(day.date, startDate)

    val inRange =
        startDate != null && endDate != null &&
                day.date.after(startDate) &&
                day.date.before(endDate)

    val isStart = startDate != null && sameDay(day.date, startDate)
    val isEnd = endDate != null && sameDay(day.date, endDate)

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .clickable { onClick(day.date) }
    ) {

        // 🔷 FUNDO DO RANGE (azul claro contínuo)
        if (inRange || isStart || isEnd) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        color = Color(0xFFBBDEFB),
                        shape = when {
                            isStart -> RoundedCornerShape(
                                topStart = 50.dp,
                                bottomStart = 50.dp
                            )

                            isEnd -> RoundedCornerShape(
                                topEnd = 50.dp,
                                bottomEnd = 50.dp
                            )

                            else -> RectangleShape
                        }
                    )
            )
        }

        // 🔵 CÍRCULO AZUL (INÍCIO / FIM)
        if (isStart || isEnd) {
            Box(
                modifier = Modifier
                    .size(36.dp) // 👈 tamanho fixo
                    .align(Alignment.Center)
                    .background(
                        color = Color(0xFF1976D2),
                        shape = CircleShape
                    )
            )
        }

        // 🔢 TEXTO DO DIA
        Text(
            text = day.date.get(Calendar.DAY_OF_MONTH).toString(),
            modifier = Modifier.align(Alignment.Center),
            color = if (day.isCurrentMonth) Color.Black else Color.Gray,
            fontWeight = if (isStart || isEnd) FontWeight.Bold else FontWeight.Normal
        )
    }
}

data class CalendarDay(
    val date: Calendar,
    val isCurrentMonth: Boolean
)

fun generateMonthDays(
    year: Int,
    month: Int
): List<CalendarDay> {

    val firstOfMonth = Calendar.getInstance().apply {
        firstDayOfWeek = Calendar.SUNDAY
        clear()
        set(year, month, 1)
    }

    val offset =
        (firstOfMonth.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY + 7) % 7

    val cal = firstOfMonth.clone() as Calendar
    cal.add(Calendar.DAY_OF_MONTH, -offset)

    return List(42) {
        CalendarDay(
            date = cal.clone() as Calendar,
            isCurrentMonth = cal.get(Calendar.MONTH) == month
        ).also {
            cal.add(Calendar.DAY_OF_MONTH, 1)
        }
    }
}

@Composable
fun CalendarHeader(
    month: Calendar,
    onPrev: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        IconButton(onClick = onPrev) {
            Icon(Icons.Default.KeyboardArrowLeft, null)
        }

        Text(
            text = SimpleDateFormat(
                "MMMM yyyy",
                Locale("pt", "BR")
            ).format(month.time),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        IconButton(onClick = onNext) {
            Icon(Icons.Default.KeyboardArrowRight, null)
        }
    }
}

fun handleDateClick(
    clicked: Calendar,
    start: Calendar?,
    end: Calendar?,
    onUpdate: (Calendar?, Calendar?) -> Unit
) {
    when {
        start == null -> onUpdate(clicked, null)
        end == null -> {
            if (clicked.before(start))
                onUpdate(clicked, start)
            else
                onUpdate(start, clicked)
        }
        else -> onUpdate(clicked, null)
    }
}

@Composable
fun CalendarRangeDialog(
    onDismiss: () -> Unit,
    onConfirm: (Calendar, Calendar) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 120 }
    )
    val baseCalendar = remember {
        Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
        }
    }

    var startDate by remember { mutableStateOf<Calendar?>(null) }
    var endDate by remember { mutableStateOf<Calendar?>(null) }
    val scope = rememberCoroutineScope()

    Dialog(onDismissRequest = onDismiss) {

        Surface(shape = RoundedCornerShape(12.dp)) {

            Column(modifier = Modifier.padding(16.dp)) {

                val monthCal = baseCalendar.clone() as Calendar
                monthCal.add(Calendar.MONTH, pagerState.currentPage)

                CalendarHeader(
                    month = monthCal,
                    onPrev = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage - 1
                            )
                        }
                    },
                    onNext = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    }
                )

                HorizontalPager(
                    state = pagerState
                ) { page ->

                    val cal = baseCalendar.clone() as Calendar
                    cal.add(Calendar.MONTH, page)

                    MonthCalendar(
                        year = cal.get(Calendar.YEAR),
                        month = cal.get(Calendar.MONTH),
                        startDate = startDate,
                        endDate = endDate
                    ) { clicked ->
                        handleDateClick(
                            clicked,
                            startDate,
                            endDate
                        ) { s, e ->
                            startDate = s
                            endDate = e
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                    TextButton(
                        enabled = startDate != null && endDate != null,
                        onClick = {
                            onConfirm(startDate!!, endDate!!)
                        }
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarRangeContent(
    onConfirm: (Calendar, Calendar) -> Unit = { _, _ -> },
    onCancel: () -> Unit = {}
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 12 }
    )

    val scope = rememberCoroutineScope()

    val baseCalendar = remember {
        Calendar.getInstance().apply {
            clear()
            set(Calendar.DAY_OF_MONTH, 1)
        }
    }

    var startDate by remember { mutableStateOf<Calendar?>(null) }
    var endDate by remember { mutableStateOf<Calendar?>(null) }

    val monthCal = remember(pagerState.currentPage) {
        baseCalendar.clone() as Calendar
    }.apply {
        add(Calendar.MONTH, pagerState.currentPage)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {

        // HEADER
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage - 1
                        )
                    }
                }
            ) {
                Icon(Icons.Default.KeyboardArrowLeft, null)
            }

            Text(
                text = SimpleDateFormat(
                    "MMMM yyyy",
                    Locale("pt", "BR")
                ).format(monthCal.time),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                }
            ) {
                Icon(Icons.Default.KeyboardArrowRight, null)
            }
        }

        Spacer(Modifier.height(8.dp))

        // PAGER
        HorizontalPager(state = pagerState) { page ->

            val cal = baseCalendar.clone() as Calendar
            cal.add(Calendar.MONTH, page)

            MonthCalendar(
                year = cal.get(Calendar.YEAR),
                month = cal.get(Calendar.MONTH),
                startDate = startDate,
                endDate = endDate
            ) { clicked ->
                handleDateClick(
                    clicked,
                    startDate,
                    endDate
                ) { s, e ->
                    startDate = s
                    endDate = e
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // BOTÕES
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onCancel) {
                Text("Cancelar")
            }
            TextButton(
                enabled = startDate != null && endDate != null,
                onClick = {
                    onConfirm(startDate!!, endDate!!)
                }
            ) {
                Text("OK")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun CalendarRangePreview() {
    CalendarRangeContent()
}