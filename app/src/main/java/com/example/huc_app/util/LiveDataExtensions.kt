package com.example.huc_app.util

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}
