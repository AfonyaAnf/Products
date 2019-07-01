package com.products.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.products.ProductApplication
import com.products.R
import com.products.di.CommonComponent
import com.products.di.DaggerCommonComponent
import com.products.di.NavigationModule
import com.products.ui.fragment.ProductListFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var commonComponent: CommonComponent

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        commonComponent = DaggerCommonComponent.builder()
            .applicationComponent(ProductApplication.instance.applicationComponent)
            .navigationModule(NavigationModule(supportFragmentManager, R.id.contentLayout))
            .build()
        commonComponent.inject(this)

        if (savedInstanceState == null)
            navigationManager.openAsRoot(ProductListFragment.getInstance())
    }
}
