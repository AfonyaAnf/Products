package com.products.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
class NavigationManager(private val mFragmentManager: FragmentManager, private val container: Int) {

    fun open(fragment: Fragment) {
        openFragment(fragment, true, false)

    }

    private fun openFragment(fragment: Fragment, addToBackStack: Boolean, isRoot: Boolean) {
        val fragTransaction = mFragmentManager.beginTransaction()

        if (isRoot)
            fragTransaction.replace(container, fragment, "ROOT")
        else
            fragTransaction.replace(container, fragment)

        if (addToBackStack)
            fragTransaction.addToBackStack(fragment.toString())
        fragTransaction.commit()
    }

    fun openAsRoot(fragment: Fragment) {
        popEveryFragment()
        openFragment(fragment, false, true)
    }

    private fun popEveryFragment() {
        mFragmentManager.popBackStackImmediate("ROOT", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


    fun navigateBack(): Boolean {
        if (mFragmentManager.backStackEntryCount == 0) {
            return false
        } else {
            mFragmentManager.popBackStackImmediate()
            return true
        }
    }


}