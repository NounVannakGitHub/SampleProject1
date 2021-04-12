package kh.com.wbfinance.vannak.sampleproject1.ui.overview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.data.helper.CaseType
import kh.com.wbfinance.vannak.sampleproject1.databinding.ActivityDashboardBinding
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.createAdapter
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.*
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseActivity
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.ui.dailygraph.DailyGraphActivity
import kh.com.wbfinance.vannak.sampleproject1.ui.detail.DetailActivity
import kh.com.wbfinance.vannak.sampleproject1.ui.percountry.PerCountryActivity
import kh.com.wbfinance.vannak.sampleproject1.util.ext.observe
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardActivity : BaseActivity() {

    private val viewModel by viewModel<DashboardViewModel>()
    private val dailyAdapter by lazy {
        createAdapter(
            ::onItemClicked
        )
    }
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadDashboard()
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        with(binding) {
            recyclerView.adapter = dailyAdapter
            recyclerView.setHasFixedSize(true)

            swipeRefresh.setOnRefreshListener { viewModel.loadDashboard() }
            fab.setOnClickListener { permission(CaseType.FULL) }
        }
    }

    private fun permission(state: Int) {
        permission { DetailActivity.startActivity(this, state) }
    }

    override fun observeChange() {
        observe(viewModel.loading, ::handleLoading)
        observe(viewModel.items, ::onDataLoaded)
        observe(viewModel.toastMessage, ::showSnackbarMessage)
    }

    private fun handleLoading(status: Boolean) {
        binding.swipeRefresh.isRefreshing = status
    }

    private fun onDataLoaded(items: List<BaseViewItem>) {
        dailyAdapter.submitList(items)
    }

    private fun onItemClicked(viewItem: BaseViewItem, view: View) {
        when (viewItem) {
            is OverviewItem -> {
                when (view.id) {
                    R.id.layout_active -> permission(CaseType.CONFIRMED)
                    R.id.layout_recovered -> permission(CaseType.RECOVERED)
                    R.id.layout_death -> permission(CaseType.DEATHS)
                }
            }
            is DailyItem -> {
                Log.e("DailyItem", "DailyItem Click: ${viewItem.deltaConfirmed}")
            }
            is CountryItem -> {
                /*Assuming every local country data has different API, so we provide dedicated activity,
                * But with reusable components*/
                PerCountryActivity.startActivity(this,viewItem.country)
            }
            is TextItem -> {
                DailyGraphActivity.startActivity(this)
            }
            is ErrorStateItem -> {
                viewModel.loadDashboard()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                when (item.itemId) {
                    R.id.action_update -> getString(R.string.update_url)
                    else -> getString(R.string.feedback_url)
                }
            )
        )
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}