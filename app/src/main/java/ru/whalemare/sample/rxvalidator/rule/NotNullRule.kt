package ru.whalemare.sample.rxvalidator.rule

import ru.whalemare.rxvalidator.ValidateRule


/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class NotNullRule : ValidateRule {
    override fun errorMessage() = "Text must not be null"

    override fun validate(data: String?): Boolean {
        return data != null
    }
}