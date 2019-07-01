package com.products.ui.viewmodel

import com.products.ProductRepository
import com.products.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
class ProductListViewModel @Inject constructor(
    val productRepository: ProductRepository
) : BaseViewModel() {

    val productList = productRepository.getAll()

    fun delete(product: Product) {
        disposables.add(
            productRepository.delete(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {})
        )

    }
}