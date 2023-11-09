package com.example.huc_app.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.huc_app.R
import com.example.huc_app.ui.base.BaseAdapter

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


