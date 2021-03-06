package kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders

import android.view.View
import com.bumptech.glide.Glide
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.data.helper.CaseType
import kh.com.wbfinance.vannak.sampleproject1.data.helper.CaseTypes
import kh.com.wbfinance.vannak.sampleproject1.databinding.ItemLocationBinding
import kh.com.wbfinance.vannak.sampleproject1.model.dao.CountryInfo
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import kh.com.wbfinance.vannak.sampleproject1.util.NumberUtils
import kh.com.wbfinance.vannak.sampleproject1.util.ext.visible
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

data class LocationItem(
    val confirmed: Int = 0,
    val recovered: Int = 0,
    val deaths: Int = 0,
    val locationName: String,
    val lastUpdate: Long,
    val lat: Double,
    val long: Double,
    val countryRegion: String,
    val provinceState: String?,
    val countryInfo: CountryInfo?,
    @CaseTypes val caseType: Int,
    val isPinned: Boolean = false
) : BaseViewItem {
    fun compositeKey() = countryRegion + provinceState
}

class LocationItemViewHolder(itemView: View) : RecyclerViewHolder<LocationItem>(itemView) {
    val binding: ItemLocationBinding = ItemLocationBinding.bind(itemView)


    override fun bind(position: Int, item: LocationItem) {
        super.bind(position, item)
        with(binding) {

            relativePinned.visibility = if (item.isPinned) View.VISIBLE else View.GONE

            val context = itemView.context
            txtLocation.text = item.locationName
            txtInformation.text = context.getString(
                R.string.information_last_update,
                NumberUtils.formatTime(item.lastUpdate)
            )
            txtData.text = context.getString(
                R.string.confirmed_case_count,
                NumberUtils.numberFormat(item.confirmed)
            )
            txtRcv.text = context.getString(
                R.string.recovered_case_count,
                NumberUtils.numberFormat(item.recovered)
            )
            txtDeath.text = context.getString(
                R.string.death_case_count,
                NumberUtils.numberFormat(item.deaths)
            )
            Glide.with(itemView.context).load(item.countryInfo?.flag).error(R.drawable.ic_location).into(imgIcon)

            when (item.caseType) {
                CaseType.CONFIRMED -> txtData.visible()
                CaseType.RECOVERED -> txtRcv.visible()
                CaseType.DEATHS -> txtDeath.visible()
                else -> {
                    txtData.visible()
                    txtRcv.visible()
                    txtDeath.visible()
                }
            }
        }
    }
}