package ru.whalemare.sample.rxvalidator.rule

import ru.whalemare.rxvalidator.ValidateRule

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class NotEmptyRule : ValidateRule {
    override fun errorMessage() = "Text must not be null"

    override fun validate(data: String?): Boolean {
        if (!NotNullRule().validate(data)) return false

        return data!!.isNotEmpty()
    }

}