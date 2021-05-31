package ru.whalemare.rxvalidator

import android.text.Editable
import android.text.TextWatcher

/**
 * @since 2020
 * @author Steve M - steve1rm
 */
abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { /* no-op */ }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) { /* no-op */ }

    override fun afterTextChanged(p0: Editable?) { /* no-op */ }
}
