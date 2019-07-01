package com.products

import android.app.Application
import android.arch.persistence.room.Room
import com.products.db.ProductDataBase
import com.products.di.ApplicationComponent
import com.products.di.ApplicationModule
import com.products.di.DaggerApplicationComponent


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
class ProductApplication : Application() {

    companion object {
        private const val DATABASE_NAME = "DATABASE_NAME"
        lateinit var instance: ProductApplication
    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        val database = Room.databaseBuilder(applicationContext, ProductDataBase::class.java, DATABASE_NAME)
            .build()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(database))
            .build()
    }
}