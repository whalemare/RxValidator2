package ru.whalemare.rxvalidator

import org.junit.Before
import org.junit.Test

/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
@Suppress("unused", "IllegalIdentifier")
open class Benchmark {

    lateinit var validator: Validator

    @Before
    fun setup() {
        validator = Validator()
    }

    @Test
    fun `benchmark validation list`() {
        validator.apply {
            add(object : ValidateRule {
                override fun validate(data: String?) = true

                override fun errorMessage(): String {
                    TODO("not should be called")
                }
            })
            add(object : ValidateRule {
                override fun validate(data: String?) = true

                override fun errorMessage(): String {
                    TODO("not should be called")
                }
            })
            add(object : ValidateRule {
                override fun validate(data: String?) = true

                override fun errorMessage(): String {
                    TODO("not should be called")
                }
            })
        }

        benchmark(30) {
            validator.validate("some data", {

            }, {

            })
        }
    }

    fun benchmark(count: Long, run: () -> Unit) {
        (0 until count).forEach {
            run.invoke()
        }
    }

}