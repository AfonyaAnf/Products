package com.products.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.products.di.CommonComponent
import com.products.ui.MainActivity
import kotlinx.android.synthetic.main.layout_appbar.*


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
abstract class BaseFragment : Fragment() {

    open fun isShowBackButton() = false

    abstract fun componentCreated(component: CommonComponent)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.also { activity ->
            activity.setSupportActionBar(toolbar)
            isShowBackButton().also {
                activity.supportActionBar?.setDisplayHomeAsUpEnabled(it)
                activity.supportActionBar?.setHomeButtonEnabled(it)
            }
            toolbar.setNavigationOnClickListener { activity.onBackPressed() }
            componentCreated(activity.commonComponent)
        }
    }
}