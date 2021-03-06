package kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders

import android.view.View
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.databinding.ItemDailyCompactBinding
import kh.com.wbfinance.vannak.sampleproject1.util.NumberUtils
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class DailyCompactViewHolder(itemView: View) : RecyclerViewHolder<DailyItem>(itemView) {

    private val binding = ItemDailyCompactBinding.bind(itemView)

    override fun bind(position: Int, item: DailyItem) {
        super.bind(position, item)
        with(binding) {
            txtDate.text = NumberUtils.formatStringDate(item.reportDate)

            root.context?.let {
                txtConfirmed.text = it.getString(
                    R.string.confirmed_case_count,
                    NumberUtils.numberFormat(item.deltaConfirmed)
                )
                txtRecovered.text = it.getString(
                    R.string.recovered_case_count,
                    NumberUtils.numberFormat(item.deltaRecovered)
                )
            }
        }
    }
}