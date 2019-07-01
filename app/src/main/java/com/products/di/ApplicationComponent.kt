package com.products.di

import com.products.db.ProductDataBase
import dagger.Component


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent {
    fun provideDataBase(): ProductDataBase
}