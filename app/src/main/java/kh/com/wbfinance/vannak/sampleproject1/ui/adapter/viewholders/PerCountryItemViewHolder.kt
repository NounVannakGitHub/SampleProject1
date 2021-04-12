package kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders

import android.net.Uri
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import kh.com.wbfinance.vannak.sampleproject1.databinding.ItemPerCountryBinding
import kh.com.wbfinance.vannak.sampleproject1.model.dao.Country
import kh.com.wbfinance.vannak.sampleproject1.model.dao.CountryInfo
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

data class CountryItem(
    var country: String,
    var countryInfo: CountryInfo,
    var todayCases: Int = 0,
    var todayDeaths: Int = 0
) : BaseViewItem {

}

class PerCountryViewHolder(itemView: View) : RecyclerViewHolder<CountryItem>(itemView) {
    val binding: ItemPerCountryBinding = ItemPerCountryBinding.bind(itemView)

    override fun bind(position: Int, item: CountryItem) {
        super.bind(position, item)
        with(binding) {
            txtCountry.text = item.country
            txtInformation.text = item.todayCases.toString()
            Glide.with(itemView.context).load(Uri.parse(item.countryInfo.flag)).into(imgIcon)
        }
    }
}