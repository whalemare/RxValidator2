package ru.whalemare.rxvalidator

import io.reactivex.rxjava3.core.Observable


/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class RxCombineValidator(vararg private val observables: Observable<Boolean>) {

    /**
     * Combiner for your validations observable, which allows change UI if all observables emmit <b>true</b>
     * (use-full for change button state)
     */
    fun asObservable(): Observable<Boolean> {
        return Observable.combineLatest(observables.toMutableList(), { arrays ->
            arrays.forEach {
                if (!(it as Boolean)) {
                    return@combineLatest false
                }
            }
            return@combineLatest true
        })
    }
}