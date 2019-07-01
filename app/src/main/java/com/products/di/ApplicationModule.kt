package com.products.di

import com.products.db.ProductDataBase
import dagger.Module
import dagger.Provides


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
@Module
class ApplicationModule(val dataBase: ProductDataBase) {

    @Provides
    fun provideDataBase() = dataBase

}