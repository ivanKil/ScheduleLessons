package com.kusch.exam.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kusch.exam.model.data.Lesson
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    val ldMarkers: MutableLiveData<List<Lesson>> = MutableLiveData()
    val ldAddMarker: MutableLiveData<Lesson> = MutableLiveData()

    fun requestMarkers() {
        viewModelScope.launch {
        }
    }

    fun addMarker(lesson: Lesson) {
        viewModelScope.launch {

        }
    }
}