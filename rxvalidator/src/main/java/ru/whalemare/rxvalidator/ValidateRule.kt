package ru.whalemare.rxvalidator

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
interface ValidateRule {

    fun validate(data: String?): Boolean

    fun errorMessage(): String
}