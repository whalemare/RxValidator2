package ru.whalemare.rxvalidator.rule

import ru.whalemare.rxvalidator.ValidateRule

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class NotEmptyRule : ValidateRule {
    override fun errorMessage() = "Текст не может быть пустой"

    override fun validate(data: String?): Boolean {
        if (!NotNullRule().validate(data)) return false

        return data!!.isNotEmpty()
    }

}