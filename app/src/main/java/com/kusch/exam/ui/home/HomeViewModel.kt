package com.kusch.exam.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kusch.exam.model.data.AddLesson
import com.kusch.exam.model.data.Homework
import com.kusch.exam.model.data.Lesson
import com.kusch.exam.model.data.ListData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit


class HomeViewModel() : ViewModel() {

    val ldLessons: MutableLiveData<List<Lesson>> = MutableLiveData()
    val ldHomeworks: MutableLiveData<List<Homework>> = MutableLiveData()
    val ldSchedule: MutableLiveData<List<ListData>> = MutableLiveData()
    val ldTime: MutableLiveData<List<String>> = MutableLiveData()
    private var dateOfExam: LocalDateTime? = null
    private val MILLS_UPDATE_TIME = 60000L
    private val HOURS_IN_DAY = 24
    private val MINUTES_IN_HOUR = 60

    fun init() {
        val localDate = LocalDateTime.now()
        dateOfExam = localDate.plusDays(9)
    }

    fun requestLessons() {
        viewModelScope.launch {
            ldLessons.value = listOf<Lesson>(
                Lesson(1, "История", LocalTime.parse("10:15"), 2, true),
                Lesson(2, "Биология", LocalTime.parse("08:15"), 20, true),
                Lesson(3, "Математика", LocalTime.parse("10:15"), 45, false),
                Lesson(4, "Химия", LocalTime.parse("12:20"), 110, true),
                Lesson(5, "Физика", LocalTime.parse("14:15"), 100, false),
                Lesson(6, "Литература", LocalTime.parse("16:15"), 120, true)
            )
        }
    }

    fun requestHomeworks() {
        viewModelScope.launch {
            ldHomeworks.value = listOf<Homework>(
                Homework(
                    1, "Литература", LocalDate.parse("2022-04-01"),
                    "Прочитать 1-12 главы кникги Мастер и Маргарита"
                ),
                Homework(
                    2, "Философия", LocalDate.parse("2022-04-10"),
                    "Пофилософствовать 15-20 минут"
                ),
                Homework(
                    3, "Геометрия", LocalDate.parse("2022-04-12"),
                    "Выучить доказательство теоремы Пифагора"
                ),
                Homework(4, "Алгебра", LocalDate.parse("2022-04-11"), "Прочитать главы 1-2"),
                Homework(
                    5,
                    "Окружающий мир",
                    LocalDate.parse("2022-04-22"),
                    "Прочитать главы 12-13"
                ),
                Homework(
                    6,
                    "Литература",
                    LocalDate.parse("2022-04-05"),
                    "Прочитать Войну и мир"
                )
            )
        }
    }

    fun requestSchedule() {
        viewModelScope.launch {
            ldSchedule.value = listOf<ListData>(
                Lesson(1, "История", LocalTime.parse("10:15"), 2, true),
                AddLesson(2, "Биология", LocalTime.parse("08:15"), 20),
                Lesson(3, "Математика", LocalTime.parse("10:15"), 45, true),
                Lesson(4, "Химия", LocalTime.parse("12:20"), 110, true),
                AddLesson(5, "Физика", LocalTime.parse("14:15"), 100),
                Lesson(6, "Литература", LocalTime.parse("16:15"), 120, true)
            ).sortedBy { it.getTimeStart() }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initTimer() {
        viewModelScope.launch {
            val list = mutableListOf<String>()
            val localDate = LocalDateTime.now()
            val days = ChronoUnit.DAYS.between(localDate, dateOfExam)
            list.add(String.format("%02d", days))
            list.add(
                String.format(
                    "%02d",
                    ChronoUnit.HOURS.between(localDate, dateOfExam) % (days * HOURS_IN_DAY)
                )
            )
            list.add(
                String.format(
                    "%02d",
                    ChronoUnit.MINUTES.between(localDate, dateOfExam) % MINUTES_IN_HOUR
                )
            )
            ldTime.value = list
            delay(MILLS_UPDATE_TIME)
            initTimer()
        }
    }
}