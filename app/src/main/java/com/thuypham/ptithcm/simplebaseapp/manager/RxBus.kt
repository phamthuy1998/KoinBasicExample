package com.thuypham.ptithcm.simplebaseapp.manager

import com.thuypham.ptithcm.simplebaseapp.extension.showLog
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

interface RxBusListener {
    fun getViewUUID(): Int
}

enum class RxBus {
    INSTANCE;

    var tracking = HashMap<Any, CompositeDisposable>()

    val publisher = PublishSubject.create<Any>()

    fun post(event: Any) {
        publisher.onNext(event)
    }

    private var previousUUID: Int = 0
    fun getNewUUID() = ++previousUUID

    inline fun <reified T> listen(listener: RxBusListener, crossinline callBack: () -> Unit) {
        if (tracking[listener.getViewUUID()] == null) {
            tracking[listener.getViewUUID()] = CompositeDisposable()
        }
        "Rxbus, New event listener: ${listener.getViewUUID()}".showLog()
        tracking[listener.getViewUUID()]?.add(
            publisher.ofType(T::class.java).observeOn(AndroidSchedulers.mainThread())
                .subscribe { callBack() }
        )
    }

    inline fun <reified T> dataListen(listener: RxBusListener, crossinline callBack: (T) -> Unit) {
        if (tracking[listener.getViewUUID()] == null) {
            tracking[listener.getViewUUID()] = CompositeDisposable()
        }
        "Rxbus, New event listener: ${listener.getViewUUID()}".showLog()
        tracking[listener.getViewUUID()]?.add(
            publisher.ofType(T::class.java).observeOn(AndroidSchedulers.mainThread())
                .subscribe { callBack(it) })
    }

    fun unRegister(listener: RxBusListener) {
        tracking[listener.getViewUUID()]?.dispose()
        tracking.remove(listener.getViewUUID())?.let {
            "RxBus, removed event listener: ${listener.getViewUUID()}".showLog()
        }
    }
}

class OnRemoteConfigLoaded(val isLoadFromPrevious: Boolean, val isCurrentSuccess: Boolean)