package com.products.di

import android.support.v4.app.FragmentManager
import com.products.ui.NavigationManager
import dagger.Module
import dagger.Provides


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
@Module
class NavigationModule(val fragmentManager: FragmentManager, val containerId: Int) {
    @Provides
    fun provideNavigationManeger() = NavigationManager(fragmentManager, containerId)
}