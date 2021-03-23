package id.buaja.post.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created by Julsapargi Nursam on 3/24/21.
 */
 
fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}