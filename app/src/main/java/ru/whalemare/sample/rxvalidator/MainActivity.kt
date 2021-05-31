package ru.whalemare.sample.rxvalidator

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import io.reactivex.rxjava3.core.Observable
import ru.whalemare.rxvalidator.BuildConfig
import ru.whalemare.rxvalidator.RxCombineValidator
import ru.whalemare.rxvalidator.RxValidator
import ru.whalemare.sample.rxvalidator.rule.MinLengthRule
import ru.whalemare.sample.rxvalidator.rule.NotEmptyRule

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class MainActivity : AppCompatActivity() {

    private lateinit var editLogin: TextInputLayout
    private lateinit var editEmail: TextInputLayout
    private lateinit var button: Button
    private lateinit var textVersion: TextView
    private val mainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter.attachView(this)
        setupView()

        // disable button by first start from code or xml
        button.isEnabled = false

        // add validations rules for field login
        val loginObservable: Observable<Boolean> = RxValidator(editLogin).apply {
            add(NotEmptyRule())
            add(MinLengthRule(5))
        }.asObservable()

        // add validations rules for field email
        val emailObservable = RxValidator(editEmail).apply {
            add(NotEmptyRule())
            add(MinLengthRule(7))
        }.asObservable()


        // combine our validation observables into one
        RxCombineValidator(loginObservable, emailObservable)
                .asObservable()
                .distinctUntilChanged()
                .subscribe({ valid ->
                    button.isEnabled = valid
                })
    }

    private fun setupView() {
        editLogin = findViewById(R.id.layout_login)
        editEmail = findViewById(R.id.layout_email)
        button = findViewById(R.id.button)
        textVersion = findViewById(R.id.text_version)
        textVersion.apply {
            val version = "${getString(R.string.app_name)} ${BuildConfig.VERSION_NAME}"
            textVersion.text = version
        }
    }

    //region for model layer
    fun modelLayerValidation() {
        RxValidator.createObservable(editEmail.editText!!)
                .subscribe({
                    mainPresenter.onEmailTextChanges(it)
                })
    }

    fun onEmailValid() {
        // some logic
    }

    fun onEmailInvalid(errorMessage: String) {
        // some logic
    }

    //endregion

    /**
     * This function execute all observables, because this
     * setting text to all of 'listening' views and trigger all observables,
     * for function .combineLatest in fun start() of RxCombineValidator
     *
     * @see RxCombineValidator.start
     */
    fun emulateFirstSetDataFromApi() {
        editLogin.editText!!.setText("")
        editEmail.editText!!.setText("")
    }
}