package com.products.di

import com.products.ui.MainActivity
import com.products.ui.fragment.ProductDetailFragment
import com.products.ui.fragment.ProductListFragment
import dagger.Component


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [NavigationModule::class,
        ViewModelModule::class,
        DataBaseModule::class]
)
interface CommonComponent {
    fun inject(fragment: ProductListFragment)
    fun inject(fragment: ProductDetailFragment)
    fun inject(activity: MainActivity)
}