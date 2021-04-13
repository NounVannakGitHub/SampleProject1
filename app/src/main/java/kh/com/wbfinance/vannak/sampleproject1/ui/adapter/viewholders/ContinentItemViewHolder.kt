package kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders

import android.view.View
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.databinding.ContinentItemViewBinding
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Continent
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.util.ext.getString
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

data class ContinentItem(
    val continent: Continent
): BaseViewItem

class ContinentItemViewHolder(viewItem: View): RecyclerViewHolder<ContinentItem>(viewItem){
    val binding = ContinentItemViewBinding.bind(viewItem)
    override fun bind(position: Int, item: ContinentItem) {
        super.bind(position, item)
        with(binding){
            txtLocation.text = item.continent.continent
            txtData.text = item.continent.cases.toString()
            txtRcv.text = item.continent.recovered.toString()
            txtDeath.text = item.continent.deaths.toString()
            txtInformation.text = getString(R.string.new_case_case_count,item.continent.todayCases.toString())
        }
    }
}