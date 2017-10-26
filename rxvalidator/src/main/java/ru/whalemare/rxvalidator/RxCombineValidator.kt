package ru.whalemare.rxvalidator

import io.reactivex.Observable


/**
 * @since 2017
 * @author Anton Vlasov - whalemare
 */
class RxCombineValidator(vararg val observables: Observable<Boolean>) {

    fun asObservable(): Observable<Boolean> {
        return Observable.combineLatest(observables, { arrays ->
            arrays.forEach {
                if (!(it as Boolean)) {
                    return@combineLatest false
                }
            }
            return@combineLatest true
        })
    }
}