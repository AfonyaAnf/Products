package com.products.ui.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
abstract class BaseViewModel : ViewModel() {
    val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}