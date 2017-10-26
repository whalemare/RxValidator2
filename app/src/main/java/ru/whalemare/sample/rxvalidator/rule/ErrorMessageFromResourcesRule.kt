package ru.whalemare.sample.rxvalidator.rule

import android.content.res.Resources
import ru.whalemare.rxvalidator.rule.BaseResourcesRule
import ru.whalemare.sample.rxvalidator.R

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class ErrorMessageFromResourcesRule(resources: Resources): BaseResourcesRule(resources) {

    override fun errorMessageResId(): Int {
        return R.string.error_message
    }

    // always not valid
    override fun validate(data: String?): Boolean {
        return data?.contains("email", true) ?: false
    }
}