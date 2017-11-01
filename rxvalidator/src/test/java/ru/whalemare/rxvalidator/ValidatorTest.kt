package ru.whalemare.rxvalidator

import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
@Suppress("IllegalIdentifier")
class ValidatorTest {

    lateinit var validator: Validator

    @Before
    fun setup() {
        validator = Validator()
    }

    @Test
    fun `should valid if start with 0 rules`() {
        var shouldBeTrue = false
        validator.validate("some data", {
            shouldBeTrue = true
        })
        Assert.assertEquals(true, shouldBeTrue)
    }

    @Test
    fun `should throw exception if not handled onSuccess and onInvalid`() {
        var shouldBeTrue = false

        try {
            validator.validate("some data")
        } catch (e: IllegalArgumentException) {
            shouldBeTrue = true
        }

        Assert.assertEquals(true, shouldBeTrue)
    }

    @Test
    fun `should validate true`() {
        validator.addRule(object : ValidateRule {
            override fun validate(data: String?) = true

            override fun errorMessage(): String {
                TODO("not should be called")
            }
        })

        var shouldBeTrue = false
        validator.validate("some data", onSuccess = {
            shouldBeTrue = true
        })

        Assert.assertEquals(true, shouldBeTrue)
    }

    @Test
    fun `should call onSuccess once`() {
        validator.addRule(object : ValidateRule {
            override fun validate(data: String?) = true

            override fun errorMessage(): String {
                TODO("not should be called")
            }
        }).addRule(object : ValidateRule {
            override fun validate(data: String?) = true

            override fun errorMessage(): String {
                TODO("not should be called")
            }
        }).addRule(object : ValidateRule {
            override fun validate(data: String?) = true

            override fun errorMessage(): String {
                TODO("not should be called")
            }
        })

        val shouldBeCalledOnce = AtomicInteger()
        validator.validate("some data", onSuccess = {
            shouldBeCalledOnce.incrementAndGet()
        })

        Assert.assertEquals(1, shouldBeCalledOnce.get())
    }

    @Test
    fun `should call onInvalid once`() {
        val errorMessage = "errorMessage"
        validator.addRule(object : ValidateRule {
            override fun validate(data: String?) = false

            override fun errorMessage() = errorMessage
        }).addRule(object : ValidateRule {
            override fun validate(data: String?) = false

            override fun errorMessage(): String {
                TODO("not should be called")
            }
        }).addRule(object : ValidateRule {
            override fun validate(data: String?) = false

            override fun errorMessage(): String {
                TODO("not should be called")
            }
        })

        val shouldBeCalledOnce = AtomicInteger()
        validator.validate("some data", onError = {
            shouldBeCalledOnce.incrementAndGet()
        })

        Assert.assertEquals(1, shouldBeCalledOnce.get())
    }

    @Test
    fun `should pass errorMessage to onInvalid`() {
        val errorMessage = "errorMessage"
        validator.addRule(object : ValidateRule {
            override fun validate(data: String?) = false

            override fun errorMessage() = errorMessage
        })

        validator.validate("some data", onError = { message ->
            Assert.assertEquals(errorMessage, message)
        })
    }


}