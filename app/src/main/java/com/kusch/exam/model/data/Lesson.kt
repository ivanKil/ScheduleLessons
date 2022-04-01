package com.kusch.exam.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Lesson(
    var id: Int? = null,
    val name: String,
    val date: Date
) : Parcelable