package ru.whalemare.rxvalidator

/**
 * Collector for your validation rules
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
open class Validator : LinkedHashSet<ValidateRule>() {

    open fun validate(data: String,
                      onSuccess: (() -> Unit)? = null,
                      onError: ((String) -> Unit)? = null): Boolean {
        Preconditions.atLeastOneNotNull(onSuccess, onError)

        forEach {
            if (!it.validate(data)) {
                onError?.invoke(it.errorMessage())
                return false
            }
        }

        onSuccess?.invoke()
        return true
    }

    @Deprecated(
            message = "Use default public method from Set collection",
            replaceWith = ReplaceWith(expression = "add(element: ValidateRule)")
    )
    open fun addRule(rule: ValidateRule): Validator {
        add(rule)
        return this
    }
}