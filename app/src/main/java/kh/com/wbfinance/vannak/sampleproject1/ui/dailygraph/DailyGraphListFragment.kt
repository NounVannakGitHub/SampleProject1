package kh.com.wbfinance.vannak.sampleproject1.ui.dailygraph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.databinding.FragmentDailyGraphListBinding
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.createAdapter
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseFragment
import kh.com.wbfinance.vannak.sampleproject1.util.ext.observe
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DailyDataListFragment : BaseFragment() {

    private val viewModel by sharedViewModel<DailyGraphViewModel>()

    lateinit var binding: FragmentDailyGraphListBinding

    private val dailyAdapter by lazy { createAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyGraphListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = dailyAdapter
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadRemoteDailyData()
        }
    }

    override fun observeChange() {
        observe(viewModel.loading, ::swipeLoading)
        observe(viewModel.dailyItemsVH) { items ->
            dailyAdapter.submitList(items)
        }
    }

    private fun swipeLoading(loading: Boolean) {
        with(binding.swipeRefresh) {
            post { isRefreshing = loading }
        }
    }

    companion object {
        fun newInstance() = DailyDataListFragment()
    }

}