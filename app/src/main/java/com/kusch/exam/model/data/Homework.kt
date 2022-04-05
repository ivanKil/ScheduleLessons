package com.kusch.exam.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
data class Homework(
    var id: Int? = null,
    val name: String,
    val time: LocalDate,
    val description: String
) : Parcelable