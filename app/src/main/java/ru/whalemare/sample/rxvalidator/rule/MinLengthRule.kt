package ru.whalemare.sample.rxvalidator.rule

import ru.whalemare.rxvalidator.ValidateRule

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class MinLengthRule(val count: Int): ValidateRule {
    override fun validate(data: String?): Boolean {
        return data?.length ?: 0 >= count
    }

    override fun errorMessage() = "Text must be smaller than $count"
}