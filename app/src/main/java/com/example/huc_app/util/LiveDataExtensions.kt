package com.example.huc_app.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<Event<T>>.postEvent(content: T) {
    postValue(Event(content))
}

inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner) { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) }
}

fun <T> MutableLiveData<T>.asEvent(): MutableLiveData<Event<T>> {
    val eventLiveData = MutableLiveData<Event<T>>()
    this.observeForever { event ->
        eventLiveData.value = Event(event)
    }
    return eventLiveData
}

