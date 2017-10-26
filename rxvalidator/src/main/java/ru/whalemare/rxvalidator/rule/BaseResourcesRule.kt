package ru.whalemare.rxvalidator.rule

import android.content.res.Resources
import ru.whalemare.rxvalidator.ValidateRule

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
abstract class BaseResourcesRule(val resources: Resources): ValidateRule {

    abstract fun errorMessageResId(): Int

    override fun errorMessage(): String {
        return resources.getString(errorMessageResId())
    }
}