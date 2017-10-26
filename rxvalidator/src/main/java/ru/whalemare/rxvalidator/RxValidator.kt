package ru.whalemare.rxvalidator

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.Observable

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class RxValidator(val inputLayout: TextInputLayout) : Validator() {

    fun validate(): Observable<Boolean> {
        return createObservable(editText).map {
            validate(it, onSuccess = {
                inputLayout.error = null
            }, onInvalid = { message ->
                inputLayout.error = message
            })
        }
    }

    private val editText by lazy {
        inputLayout.editText ?: throw NullPointerException(
                "Your ${TextInputLayout::class.java.simpleName} should has EditText as child"
        )
    }

    private fun createObservable(editText: EditText): Observable<String> {
        return Observable.create {
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    // ignore
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // ignore
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    it.onNext(s.toString())
                }
            })
        }
    }

}