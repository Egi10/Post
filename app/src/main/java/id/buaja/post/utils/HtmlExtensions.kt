package id.buaja.post.utils

import androidx.core.text.HtmlCompat

/**
 * Created by Julsapargi Nursam on 3/24/21.
 */

fun String.toHtml(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}