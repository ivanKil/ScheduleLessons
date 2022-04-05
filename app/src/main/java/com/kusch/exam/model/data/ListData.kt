package com.kusch.exam.model.data

import java.time.LocalTime

const val TYPE_LESSON = 0
const val TYPE_ADD_LESSON = 1

interface ListData {
    fun getType(): Int
    fun getTimeStart(): LocalTime
}