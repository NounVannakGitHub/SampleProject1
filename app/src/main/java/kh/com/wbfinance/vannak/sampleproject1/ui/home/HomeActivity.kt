package kh.com.wbfinance.vannak.sampleproject1.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import kh.com.wbfinance.vannak.sampleproject1.databinding.ActivityHomeBinding
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseActivity
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.ui.custom.SimpleOnTabSelectedListener
import kh.com.wbfinance.vannak.sampleproject1.util.ext.observe
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    companion object {

        private const val CURRENT_SCREEN = "current_screen"

        const val HOME = 0
        const val SEARCH = 1
        const val ACCOUNT = 2

        fun getStartIntent(context: Context, isNewTask: Boolean = false): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            if (isNewTask) {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            return intent
        }
    }

    private var currentScreen: Int = HOME
    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentScreen = savedInstanceState?.getInt(CURRENT_SCREEN, HOME) ?: HOME
        setupNavigation()
        switchFragment(currentScreen)
        viewModel.fetchCategoryWithExistProductInside()
    }

    override fun observeChange() {
        observe(viewModel.loading, ::loading)
        observe(viewModel.errorMessage, ::showSnackbarMessage)
        observe(viewModel.categoryListViewItems, ::onListChanged)
    }

    private fun onListChanged(data: List<BaseViewItem>){
        // update adapter
    }
    private fun setupNavigation() {
        binding.bottomTabNavigation.getTabAt(currentScreen)?.select()
        binding.bottomTabNavigation.addOnTabSelectedListener(object : SimpleOnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                switchFragment(tab.position)
            }
        })
    }

    override fun onBackPressed() {
        if (binding.bottomTabNavigation.selectedTabPosition == SEARCH) {
//            val searchFragment: Fragment? = supportFragmentManager.findFragmentByTag(SEARCH.toString())
//            if (searchFragment is SearchWithCategorieFragment && searchFragment.onBackPressed()) {
                selectTab(HOME)
//            }
        } else if (binding.bottomTabNavigation.selectedTabPosition != HOME) {
            selectTab(HOME)
        } else {
            finish()
        }
    }

    private fun switchFragment(position: Int) {
        currentScreen = position
//        supportFragmentManager.replaceByTag(R.id.content, position.toString(), {
//            when (position) {
//                HOME -> HomeFragment()
//                SEARCH -> SearchWithCategoriesFragment()
//                else -> AccountFragment()
//            }
//        }).commit()
    }

    private fun selectTab(position: Int) {
        binding.bottomTabNavigation.getTabAt(position)?.select()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(CURRENT_SCREEN, currentScreen)
    }
}