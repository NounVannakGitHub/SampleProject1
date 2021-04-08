package kh.com.wbfinance.vannak.sampleproject1.ui.dailygraph

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.databinding.DailyGraphActivityBinding
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseActivity
import kh.com.wbfinance.vannak.sampleproject1.ui.dailygraph.ui.main.DailyGraphFragment
import kh.com.wbfinance.vannak.sampleproject1.ui.dailygraph.ui.main.DailyGraphViewModel
import kh.com.wbfinance.vannak.sampleproject1.util.ext.observe
import org.koin.android.viewmodel.ext.android.viewModel

class DailyGraphActivity : BaseActivity(), DailyGraphFragment.DailyListener {

    private val viewModel by viewModel<DailyGraphViewModel>()
    private lateinit var binding: DailyGraphActivityBinding

    private var singlePane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DailyGraphActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        viewModel.loadCacheDailyData()
    }

    private fun initView() {
        val frameLayout: View? = findViewById(R.id.frame_layout)
        singlePane = frameLayout?.visibility == View.VISIBLE
        if (singlePane) {
            val fragment = DailyGraphFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.frame_layout, fragment, "graph_fragment").commit()
        }
        binding.fabBack.setOnClickListener { onBackPressed() }
    }

    override fun observeChange() {
        observe(viewModel.toastMessage, ::showSnackbarMessage)
    }

    override fun onSwap() {
        if (singlePane) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_right, 0, 0, R.anim.slide_out_right)
            transaction.replace(R.id.frame_layout, DailyDataListFragment.newInstance(), "list_fragment")
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    companion object {
        fun startActivity(context: Context?) = context?.startActivity(
            Intent(context, DailyGraphActivity::class.java)
        )
    }
}