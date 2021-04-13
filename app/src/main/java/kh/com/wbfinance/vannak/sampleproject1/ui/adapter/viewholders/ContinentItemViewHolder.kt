package kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders

import android.view.View
import kh.com.wbfinance.vannak.sampleproject1.databinding.ContinentItemViewBinding
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Continent
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

data class ContinentItem(
    val continent: Continent
): BaseViewItem

class ContinentItemViewHolder(viewItem: View): RecyclerViewHolder<ContinentItem>(viewItem){
    val binding = ContinentItemViewBinding.bind(viewItem)
    override fun bind(position: Int, item: ContinentItem) {
        super.bind(position, item)
        with(binding){
            txtContinent.text = item.continent.continent
            txtConfirmed.text = item.continent.cases.toString()
            txtRecovered.text = item.continent.recovered.toString()
            txtDeath.text = item.continent.deaths.toString()
            txtTodayCases.text = item.continent.todayCases.toString()
        }
    }
}