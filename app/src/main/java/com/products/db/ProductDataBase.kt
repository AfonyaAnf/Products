package com.products.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.products.model.Product


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
@Database(entities = [Product::class], version = 1)
abstract class ProductDataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}