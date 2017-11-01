package ru.whalemare.sample.rxvalidator.rule

import ru.whalemare.rxvalidator.ValidateRule
import java.util.regex.Pattern

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
open class PatternRule(val pattern: Pattern) : ValidateRule {

    override fun errorMessage(): String {
        return "Pattern $pattern not valid"
    }

    override fun validate(data: String?): Boolean {
        return pattern.matcher(data).matches()
    }

}
