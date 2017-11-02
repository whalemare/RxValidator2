package ru.whalemare.rxvalidator

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
interface ValidateRule {

    /**
     * Method for your validation logic
     */
    fun validate(data: String?): Boolean

    /**
     * The error message that will be sent, if the <b>validate</b> method returns <b>false</b>
     */
    fun errorMessage(): String
}