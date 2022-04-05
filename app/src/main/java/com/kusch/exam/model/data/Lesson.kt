package com.kusch.exam.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalTime

@Parcelize
data class Lesson(
    var id: Int? = null,
    val name: String,
    val time: LocalTime,
    val duration: Long,
    val mayCall: Boolean
) : Parcelable, ListData {
    override fun getType() = TYPE_LESSON
    override fun getTimeStart() = time
}

@Parcelize
data class AddLesson(
    var id: Int? = null,
    val name: String,
    val time: LocalTime,
    val duration: Long
) : Parcelable, ListData {
    override fun getType() = TYPE_ADD_LESSON
    override fun getTimeStart() = time
}