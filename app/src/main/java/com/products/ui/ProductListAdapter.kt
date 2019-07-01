package com.products.ui

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.products.R
import com.products.model.Product
import com.products.repository.toStringOrEmpty
import javax.inject.Inject


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
class ProductListAdapter @Inject constructor() : RecyclerView.Adapter<ProductViewHolder>() {

    var onClickListener: ((Product) -> Unit)? = null
    var items: MutableList<Product> = mutableListOf()
        set(value) {
            val diffCallback = ProductDiffCallBack(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field.clear()
            field.addAll(value)
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductViewHolder {
        val productViewHolder = ProductViewHolder(p0)
        productViewHolder.itemView.setOnClickListener {
            items[productViewHolder.adapterPosition]?.let { onClickListener?.invoke(it) }
        }
        return productViewHolder
    }

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(p0: ProductViewHolder, p1: Int) {
        p0.updateData(items[p1])
    }
}

class ProductViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_item_list, viewGroup, false)
) {
    fun updateData(data: Product) {
        itemView.findViewById<TextView>(R.id.name).text = data.name
        itemView.findViewById<TextView>(R.id.price).text = data.price.toStringOrEmpty()
        itemView.findViewById<TextView>(R.id.count).text = data.count.toStringOrEmpty()
    }
}