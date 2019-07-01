package com.products.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.products.model.Product


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id LIMIT 1")
    fun get(id: Int): Product

    @Insert
    fun insert(product: Product): Long

    @Update
    fun update(product: Product): Int

    @Delete
    fun delete(product: Product): Int
}