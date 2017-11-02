package ru.whalemare.sample.rxvalidator

import ru.whalemare.rxvalidator.Validator
import ru.whalemare.sample.rxvalidator.rule.NotEmptyRule
import ru.whalemare.sample.rxvalidator.rule.NotNullRule

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class MainPresenter {

    // please, imagine that MainActivity = View interface
    private var view: MainActivity? = null

    val validator = Validator().apply {
        add(NotNullRule())
        add(NotEmptyRule())
    }

    fun onEmailTextChanges(text: String) {
        validator.validate(text,
                onSuccess = {
                    view?.onEmailValid()
                },
                onError = { errorMessage ->
                    view?.onEmailInvalid(errorMessage)
                })
    }


    fun attachView(mainActivity: MainActivity) {
        this.view = mainActivity
    }
}