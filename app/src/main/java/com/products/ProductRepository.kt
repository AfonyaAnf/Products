package com.products

import android.arch.lifecycle.LiveData
import com.products.db.ProductDao
import com.products.model.Product
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {

    fun get(id: Int): Observable<Product> {
        return Observable.fromCallable { productDao.get(id) }
    }

    fun getAll(): LiveData<List<Product>> = productDao.getAll()

    fun save(product: Product): Observable<Int> {
        return Observable.fromCallable { productDao.update(product) }
    }

    fun create(product: Product): Observable<Long> {
        return Observable.fromCallable { productDao.insert(product) }
    }

    fun delete(product: Product): Observable<Int> {
        return Observable.fromCallable { productDao.delete(product) }
    }

}