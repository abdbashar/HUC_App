package com.example.huc_app.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.huc_app.R
import com.example.huc_app.domain.types.Language
import com.example.huc_app.domain.types.PaymentStatus
import com.example.huc_app.ui.base.BaseAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily
import java.text.ParseException
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

@BindingAdapter("showIfTrue")
fun showIfTrue(view: View, condition: Boolean) {
    view.isVisible = condition
}

@BindingAdapter("scaleImageByLanguage")
fun scaleImageByLanguage(view: View, language: Language?) {
    view.scaleX = if (language == Language.ARABIC) -1f else 1f
}

@BindingAdapter("priceWithCurrency")
fun setPriceWithCurrency(view: TextView, price: String) {
    val currency = view.context.getString(R.string.currency_iqd)
    val formattedPrice = formatPrice(numberConverterByLanguage(price))
    val mainText = SpannableStringBuilder(formattedPrice)
    mainText.append(" ")
    val currencyStart = mainText.length
    mainText.append(currency)
    mainText.setSpan(
        RelativeSizeSpan(0.75f),
        currencyStart,
        mainText.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    view.text = mainText
}

@BindingAdapter("priceWithCurrencyAndStrikethrough")
fun setPriceWithCurrencyAndStrikethrough(view: TextView, price: String) {
    val currency = view.context.getString(R.string.currency_iqd)
    val formattedPrice =
        formatPrice(numberConverterByLanguage(price)) // Format the price using the separate function
    val spannableText = SpannableStringBuilder(formattedPrice)
    spannableText.setSpan(
        StrikethroughSpan(),
        0,
        formattedPrice.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    val relativeSize = 0.75f
    val spannableCurrency = SpannableString(currency)
    spannableCurrency.setSpan(
        RelativeSizeSpan(relativeSize),
        0,
        currency.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    spannableText.append(" ")
    spannableText.append(spannableCurrency)

    view.text = spannableText
    setPriceVisibility(view, numberConverterByLanguage(price))
}

private fun formatPrice(price: String): String {
    return price.replace(Regex("(\\d)(?=(\\d{3})+\$)"), "$1,") // Add commas to separate numbers
}

private fun setPriceVisibility(view: TextView, price: String) {
    val isVisible = (price.toIntOrNull() ?: 0) > 0
    view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}


@BindingAdapter("formattedDate")
fun setFormattedDate(textView: TextView, dateString: String) {
    if (!dateString.isNullOrEmpty()) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        val currentLanguage = SettingsService.getCurrentLanguage()
        val locale = Locale(currentLanguage.languageCode)
        val outputFormat = if (currentLanguage == Language.ARABIC) {
            SimpleDateFormat("yyyy/M/d", locale)
        } else {
            SimpleDateFormat("d/M/yyyy", locale)
        }

        try {
            val date = inputFormat.parse(dateString)
            val formattedDate = outputFormat.format(date)
            val endsOnText = textView.context.getString(R.string.ends_on_date)
            val formattedTextWithDate = String.format(endsOnText, formattedDate)

            textView.text = formattedTextWithDate
        } catch (e: ParseException) {
            textView.text = ""
        }
    } else {
        textView.text = ""
    }
}


fun numberConverterByLanguage(text: String): String {
    val currentLanguage = SettingsService.getCurrentLanguage()
    val arabicNumbers = if (currentLanguage == Language.ARABIC) {
        arrayOf("٠", "١", "٢", "٣", "٤", "٥", "٦", "٧", "٨", "٩")
    } else {
        arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    }
    return text.replace(Regex("\\d")) { arabicNumbers[it.value.toInt()] }
}

@BindingAdapter("studentEnrollmentStatus")
fun setStudentEnrollmentStatus(view: TextView, isEnrolled: Boolean) {
    view.text =
        if (isEnrolled) view.context.getString(R.string.enrolled)
        else view.context.getString(R.string.not_enrolled)
}

@BindingAdapter("studentIDValidityDrawable")
fun setStudentIDValidityDrawable(view: ImageView, isValid: Boolean) {
    val context = view.context
    val drawableRes = if (isValid) R.drawable.valid_id_status else R.drawable.invalid_id_status

    view.setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
}