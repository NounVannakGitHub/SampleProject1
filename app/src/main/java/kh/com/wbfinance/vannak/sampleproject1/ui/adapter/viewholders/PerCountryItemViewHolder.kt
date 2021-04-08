package kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.databinding.ItemPerCountryBinding
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.util.ext.getString
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

data class PerCountryItem(
    val id: Int,
    @StringRes val country: Int,
    val source: String,
    @DrawableRes val icon: Int
) : BaseViewItem {

    companion object {
        /*Generated country ID for identifier*/
        const val ID = 1
        const val MY = 2
        const val UK = 3
    }
}

class PerCountryViewHolder(itemView: View) : RecyclerViewHolder<PerCountryItem>(itemView) {
    val binding: ItemPerCountryBinding = ItemPerCountryBinding.bind(itemView)

    override fun bind(position: Int, item: PerCountryItem) {
        super.bind(position, item)
        with(binding) {
            txtCountry.text = getString(item.country)
            txtInformation.text = itemView.context.getString(R.string.source, item.source)
            imgIcon.setImageDrawable(itemView.context.resources.getDrawable(item.icon))
        }
    }
}