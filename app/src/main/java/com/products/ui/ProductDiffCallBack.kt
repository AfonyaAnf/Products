package com.products.ui

import android.support.v7.util.DiffUtil
import com.products.model.Product


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 01.07.19.
 */
class ProductDiffCallBack(private val oldProductList: List<Product>, private val newProductList: List<Product>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldProductList.size
    }

    override fun getNewListSize(): Int {
        return newProductList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProductList[oldItemPosition].id === newProductList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldProductList[oldItemPosition]
        val new = newProductList[newItemPosition]

        return old.name.equals(new.name) &&
                old.price.toString() == new.price.toString() &&
                old.count.toString() == new.count.toString()
    }
}