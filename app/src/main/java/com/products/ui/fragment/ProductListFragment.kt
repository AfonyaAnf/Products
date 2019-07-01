package com.products.ui.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.products.R
import com.products.di.CommonComponent
import com.products.model.Product
import com.products.repository.injectViewModel
import com.products.ui.NavigationManager
import com.products.ui.ProductListAdapter
import com.products.ui.viewmodel.DetailParams
import com.products.ui.viewmodel.Mode
import com.products.ui.viewmodel.ProductListViewModel
import com.products.ui.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.layout_appbar.*
import javax.inject.Inject


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
class ProductListFragment : BaseFragment() {

    companion object {
        fun getInstance(): ProductListFragment {
            return ProductListFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var navigationManager: NavigationManager
    @Inject
    lateinit var productListAdapter: ProductListAdapter
    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productList.layoutManager = LinearLayoutManager(context)
        toolbar.setTitle(R.string.product_list)
    }

    override fun componentCreated(component: CommonComponent) {
        component.inject(this)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.productList.observe(viewLifecycleOwner, Observer {
            productListAdapter.items = it?.toMutableList() ?: mutableListOf()
        })
        fab.setOnClickListener {
            navigationManager.open(
                ProductDetailFragment.getInstance(
                    DetailParams(
                        Mode.CREATE
                    )
                )
            )
        }
        productList.adapter = productListAdapter
        productListAdapter.onClickListener = { openEditDialog(it) }
    }

    private fun openEditDialog(product: Product) {
        context?.also {
            val view = LayoutInflater.from(it).inflate(R.layout.layout_edit_dialog, null)
            val dialog = AlertDialog.Builder(it)
                .setView(view)
                .create()
            view.findViewById<Button>(R.id.editButton).setOnClickListener {
                navigationManager.open(
                    ProductDetailFragment.getInstance(
                        DetailParams(Mode.EDIT, product.id)
                    )
                )
                dialog.dismiss()
            }
            view.findViewById<Button>(R.id.deleteButton).setOnClickListener {
                viewModel.delete(product)
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}