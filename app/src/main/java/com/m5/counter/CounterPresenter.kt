package com.m5.counter


class CounterPresenter {
    private val model = CounterModel()
    private var view: CounterView? = null

    fun attachView(view: CounterView) {
        this.view = view
        view.updateCounter(model.getCount(), "#FFFFFF")
    }
    fun detachView() {
        view = null
    }
    fun onIncrementClicked() {
        model.increment()
        counterEffects()
    }
    fun onDecrementClicked() {
        model.decrement()
        counterEffects()

    }
    private fun counterEffects(){

        if (model.getCount() == 15){
            view?.updateCounter(model.getCount(), "#008000")
        } else {
            view?.updateCounter(model.getCount(), "#FFFFFF")
        }

        if (model.getCount() == 10){
            view?.showToast("Поздравляем!")
        }
    }

}