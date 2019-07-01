package com.products.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.products.ProductRepository
import com.products.SingleLiveEvent
import com.products.model.Product
import com.products.repository.toStringOrEmpty
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.Serializable
import javax.inject.Inject


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
class DetailProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {
    val nameLiveData = MutableLiveData<String>()
    val countLiveData = MutableLiveData<String>()
    val priceLiveData = MutableLiveData<String>()
    val closeScreen = SingleLiveEvent<Unit>()

    var params: DetailParams? = null
        set(value) {
            field = value
            value?.id?.let {
                disposables.add(
                    productRepository.get(it)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            nameLiveData.value = it.name
                            priceLiveData.value = it.price.toStringOrEmpty()
                            countLiveData.value = it.count.toStringOrEmpty()
                        }, {})
                )
            }
        }

    fun save(name: String, count: String, price: String) {
        val product = Product(params?.id, name, count.toIntOrNull(), price.toIntOrNull())
        disposables.add(
            when (params?.mode) {
                Mode.CREATE -> productRepository.create(product)
                Mode.EDIT -> productRepository.save(product)
                else -> Observable.empty()
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    closeScreen.call()
                }, {})
        )
    }
}

data class DetailParams(val mode: Mode, val id: Int? = null) : Serializable
enum class Mode { CREATE, EDIT }