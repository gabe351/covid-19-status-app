package br.com.angelorobson.application.util

import android.graphics.BitmapFactory
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.angelorobson.application.util.extensions.convertBase64ToBitmap
import br.com.angelorobson.application.util.extensions.getNumberFormat
import br.com.angelorobson.covid19.R
import com.squareup.picasso.Picasso
import java.util.*


@BindingAdapter("date")
fun convertDateToString(textView: TextView, date: Date) {
    textView.text = date.convertDateToString()
}

@BindingAdapter("dateTime")
fun convertdateTimeToString(textView: TextView, date: Date) {
    textView.text = date.formatToServerDateTimeDefaults()
}

@BindingAdapter(value = ["state", "uf"])
fun stateAndUf(textView: TextView, state: String, uf: String) {
    textView.text = textView.context.getString(R.string.state_and_uf, state, uf)
}

@BindingAdapter("initialDate")
fun convertInitialToString(textView: TextView, initialDate: Date) {
    textView.text = initialDate.convertDateToStringDDMMM()
}

@BindingAdapter("dateFormatTimeUpdated")
fun dateFormatTimeUpdated(textView: TextView, date: Date?) {
    textView.text = textView.context.getString(R.string.update_at, date?.formatDateTime())
}

@BindingAdapter("intToString")
fun intToString(textView: TextView, number: Int) {
    textView.text = number.getNumberFormat()
}

@BindingAdapter("convertFormatToViewDateTimeDefaults")
fun convertFormatToViewDateTimeDefaults(textView: TextView, date: Date?) {
    textView.text = textView.context.getString(R.string.update_at, date?.formatDateTime())
}

@BindingAdapter(value = ["descriptionButton", "numberTextButton"])
fun buttonTextStatus(button: Button, descriptionButton: String, numberTextButton: Int) {
    button.text =
        button.context.getString(
            R.string.button_status_text,
            descriptionButton,
            numberTextButton.getNumberFormat()
        )
}

@BindingAdapter("finalDate")
fun convertFinalDateToString(textView: TextView, finalDate: Date) {

    textView.text = finalDate.convertDateToStringDDMMM()
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .into(view)
}

@BindingAdapter("loadStateImgUrl")
fun loadStateImgUrl(view: ImageView, uf: String) {
    Picasso.get()
        .load("https://devarthurribeiro.github.io/covid19-brazil-api/static/flags/${uf}.png")
        .into(view)
}


@BindingAdapter("loadImageUrlOrBase64")
fun loadImageUrlOrBase64(view: ImageView, base64: String?) {
    if (base64 != null && base64.isNotEmpty()) {
        if (base64.startsWith("http")) {
            Picasso
                .get()
                .load(base64)
                .into(view)
            return
        }

        view.setImageBitmap(base64.convertBase64ToBitmap())
    }
}

@BindingAdapter("loadImageFromPath")
fun loadImageFromPath(view: ImageView, path: String) {
    view.setImageBitmap(BitmapFactory.decodeFile(path))
}

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}

@BindingAdapter("visible")
fun View.setVisible(show: Boolean) {
    visibility = if (show) VISIBLE else INVISIBLE
}