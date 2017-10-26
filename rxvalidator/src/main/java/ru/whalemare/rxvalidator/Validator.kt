package ru.whalemare.rxvalidator

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
open class Validator {
    protected val rules = mutableListOf<ValidateRule>()

    fun addRule(rule: ValidateRule): Validator {
        rules.add(rule)
        return this
    }

    fun validate(data: String,
                 onSuccess: (() -> Unit)? = null,
                 onInvalid: ((String) -> Unit)? = null): Boolean {
        Preconditions.atLeastOneNotNull(onSuccess, onInvalid)

        rules.forEach {
            if (!it.validate(data)) {
                onInvalid?.invoke(it.errorMessage())
                return false
            }
        }

        onSuccess?.invoke()
        return true
    }
}