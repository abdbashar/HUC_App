package com.example.huc_app.util

import android.text.*
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.huc_app.R
import com.example.huc_app.ui.base.BaseAdapter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let { url ->
        Glide.with(imageView).load(url).placeholder(R.drawable.loading)
            .error(R.drawable.image_place_holder).into(imageView)
    }
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
    view.scrollToPosition(0)
}

@BindingAdapter(value = ["app:dateText"], requireAll = true)
fun setDateText(textView: TextView, dateString: String?) {
    if (!dateString.isNullOrEmpty()) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        val currentDate = Calendar.getInstance().time
        val diffInMs = currentDate.time - date.time
        val diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs)
        val diffInMin = TimeUnit.MILLISECONDS.toMinutes(diffInMs)
        val diffInHr = TimeUnit.MILLISECONDS.toHours(diffInMs)
        val diffInDay = TimeUnit.MILLISECONDS.toDays(diffInMs)

        val formattedDate = when {
            diffInSec < 60 -> textView.context.getString(R.string.now)
            diffInMin < 60 -> String.format(
                textView.context.getString(R.string.minutes_ago), diffInMin
            )
            diffInHr < 24 -> String.format(
                textView.context.getString(R.string.hours_ago), diffInHr
            )
            diffInDay < 7 -> String.format(
                textView.context.getString(R.string.days_ago), diffInDay
            )
            else -> String.format(
                textView.context.getString(R.string.weeks_ago), (diffInDay / 7)
            )
        }

        textView.text = numberConverterByLanguage(formattedDate)
    }
}


