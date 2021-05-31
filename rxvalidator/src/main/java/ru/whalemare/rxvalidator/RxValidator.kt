package ru.whalemare.rxvalidator

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.rxjava3.core.Observable

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
open class RxValidator(private val inputLayout: TextInputLayout) : Validator() {

    /**
     * It`s return new validation observable, that start validate, after your subscription
     * @return new validation observable
     */
    fun asObservable(): Observable<Boolean> {
        return createObservable(editText).map {
            validate(it, onSuccess = {
                inputLayout.error = null
            }, onError = { message ->
                inputLayout.error = message
            })
        }
    }

    open protected val editText by lazy {
        inputLayout.editText ?: throw NullPointerException(
                "Your ${TextInputLayout::class.java.simpleName} should has EditText as child"
        )
    }

    companion object {
        /**
         * @return represent EditText TextWatcher as Observable<String>, that emmit elements by onTextChanged
         */
        fun createObservable(editText: EditText): Observable<String> {
            return Observable.create {
                editText.addTextChangedListener(object : SimpleTextWatcher() {
                    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        it.onNext(s.toString())
                    }
                })
            }
        }
    }
}