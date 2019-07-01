package com.products.ui.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.*
import com.products.R
import com.products.ui.viewmodel.ViewModelFactory
import com.products.di.CommonComponent
import com.products.repository.injectViewModel
import com.products.ui.NavigationManager
import com.products.ui.viewmodel.DetailParams
import com.products.ui.viewmodel.DetailProductViewModel
import com.products.ui.viewmodel.Mode
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.layout_appbar.*
import javax.inject.Inject


/**
 * Created by Alexey Anfinogentov(anfinogentovav@altarix.ru)
 * on 30.06.19.
 */
class ProductDetailFragment : BaseFragment() {

    override fun isShowBackButton() = true

    companion object {
        private const val DATA_PARAM = "DATA_PARAM"

        fun getInstance(data: DetailParams): ProductDetailFragment {
            return ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(DATA_PARAM, data)
                }
            }
        }
    }

    private var detailParams: DetailParams? = null
    private lateinit var viewModel: DetailProductViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        detailParams = arguments?.getSerializable(DATA_PARAM) as? DetailParams
    }

    override fun componentCreated(component: CommonComponent) {
        component.inject(this)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.nameLiveData.observe(viewLifecycleOwner, Observer { editName.setText(it) })
        viewModel.countLiveData.observe(viewLifecycleOwner, Observer { editCount.setText(it) })
        viewModel.priceLiveData.observe(viewLifecycleOwner, Observer { editPrice.setText(it) })
        viewModel.closeScreen.observe(viewLifecycleOwner, Observer { navigationManager.navigateBack() })
        viewModel.params = detailParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (detailParams?.mode) {
            Mode.CREATE -> toolbar.setTitle(R.string.add_product)
            Mode.EDIT -> toolbar.setTitle(R.string.edit_product)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_detail, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.save -> {
                viewModel.save(
                    editName.text.toString(),
                    editCount.text.toString(),
                    editPrice.text.toString()
                )
                return true
            }
        }

        return false
    }
}