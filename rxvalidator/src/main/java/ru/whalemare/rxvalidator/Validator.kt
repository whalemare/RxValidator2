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
                 onError: ((String) -> Unit)? = null): Boolean {
        Preconditions.atLeastOneNotNull(onSuccess, onError)

        rules.forEach {
            if (!it.validate(data)) {
                onError?.invoke(it.errorMessage())
                return false
            }
        }

        onSuccess?.invoke()
        return true
    }
}