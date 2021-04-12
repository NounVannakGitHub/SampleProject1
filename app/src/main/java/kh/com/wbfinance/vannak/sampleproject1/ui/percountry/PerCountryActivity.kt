package kh.com.wbfinance.vannak.sampleproject1.ui.percountry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kh.com.wbfinance.vannak.sampleproject1.data.helper.UIConstant
import kh.com.wbfinance.vannak.sampleproject1.databinding.ActivityPerCountryBinding
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.createAdapter
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.DailyItem
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.TextItem
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseActivity
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.ui.dailygraph.DailyGraphActivity
import kh.com.wbfinance.vannak.sampleproject1.util.ext.observe
import org.koin.android.viewmodel.ext.android.viewModel

class PerCountryActivity : BaseActivity() {
    private val viewModel by viewModel<PerCountryActivityViewModel>()
    private lateinit var binding: ActivityPerCountryBinding
    private val viewAdapter by lazy { createAdapter(::onItemClicked) }
    private var country: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBarWithBackButton(binding.toolbar)
        initView()
        country?.let {
            binding.toolbar.title = it
            viewModel.loadData(it)
        }
    }


    private fun initView() {
        country = intent.getStringExtra(UIConstant.COUNTRY)
        with(binding.recyclerView) {
            adapter = viewAdapter
            setHasFixedSize(true)
        }
        binding.swipeRefresh.setOnRefreshListener {
            country?.let {
                binding.toolbar.title = it
                viewModel.loadData(it)
            }
        }
    }

    override fun observeChange() {
        observe(viewModel.items, ::onDataLoaded)
        observe(viewModel.toastMessage, ::showSnackbarMessage)
        observe(viewModel.loading, ::loadingSwipeRefresh)
    }

    private fun loadingSwipeRefresh(loading: Boolean) {
        with(binding.swipeRefresh) {
            post {
                isRefreshing = loading
            }
        }
    }

    private fun onDataLoaded(items: List<BaseViewItem>) {
        viewAdapter.submitList(items)
    }


    private fun onItemClicked(viewItem: BaseViewItem, view: View) {
        when (viewItem) {
            is DailyItem -> {

            }
            is TextItem -> {
                DailyGraphActivity.startActivity(this)
            }
        }
    }

    companion object {
        @JvmStatic
        fun startActivity(context: Context?,country: String?) =
            context?.startActivity(Intent(context, PerCountryActivity::class.java).putExtra(UIConstant.COUNTRY,country))
    }
}