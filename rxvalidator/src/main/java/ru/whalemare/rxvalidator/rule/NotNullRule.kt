package ru.whalemare.rxvalidator.rule

import ru.whalemare.rxvalidator.ValidateRule


/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class NotNullRule : ValidateRule {
    override fun errorMessage() = "Текст не может быть пустой"

    override fun validate(data: String?): Boolean {
        return data != null
    }
}