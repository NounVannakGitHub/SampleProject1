package kh.com.wbfinance.vannak.sampleproject1.ui.adapter

import android.view.View
import kh.com.wbfinance.vannak.sampleproject1.R
import kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders.*
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import me.ibrahimyilmaz.kiel.adapterOf

typealias OnItemClickListener = (BaseViewItem, View) -> Unit
typealias OnItemLongClickListener = (BaseViewItem, View) -> Unit

fun createAdapter(
    onItemClick: OnItemClickListener? = null,
    onItemLongClickListener: OnItemLongClickListener? = null
) = adapterOf<BaseViewItem> {
    register(
        layoutResource = R.layout.item_daily_compact,
        viewHolder = ::DailyCompactViewHolder
    )
    register(
        layoutResource = R.layout.item_daily,
        viewHolder = ::DailyItemViewHolder,
        onBindViewHolder = { dailyItemViewHolder, _, dailyItem ->
            dailyItemViewHolder.itemView.setOnClickListener { onItemClick?.invoke(dailyItem, it) }
        }
    )
    register(
        layoutResource = R.layout.item_error_state,
        viewHolder = ::ErrorStateItemViewHolder,
        onBindViewHolder = { errorStateItemViewHolder, _, errorStateItem ->
            errorStateItemViewHolder.binding.textRetry.setOnClickListener {
                onItemClick?.invoke(
                    errorStateItem,
                    it
                )
            }
        }
    )
    register(
        layoutResource = R.layout.item_loading_state,
        viewHolder = ::LoadingStateItemViewHolder
    )
    register(
        layoutResource = R.layout.item_location,
        viewHolder = ::LocationItemViewHolder,
        onBindViewHolder = { locationItemViewHolder, _, locationItem ->
            with(locationItemViewHolder) {
                binding.root.setOnClickListener { onItemClick?.invoke(locationItem, it) }
                binding.relativePinned.setOnClickListener { onItemClick?.invoke(locationItem, it) }

                binding.root.setOnLongClickListener {
                    onItemLongClickListener?.invoke(locationItem, it)
                    true
                }
            }
        }
    )
    register(
        layoutResource = R.layout.item_overview,
        viewHolder = ::OverviewItemViewHolder,
        onBindViewHolder = { overviewItemViewHolder, _, overviewItem ->
            with(overviewItemViewHolder.binding) {
                layoutActive.setOnClickListener { onItemClick?.invoke(overviewItem, it) }
                layoutRecovered.setOnClickListener { onItemClick?.invoke(overviewItem, it) }
                layoutDeath.setOnClickListener { onItemClick?.invoke(overviewItem, it) }
            }
        }
    )
    register(
        layoutResource = R.layout.item_daily_percountry_graph,
        viewHolder = ::PerCountryDailyGraphItemViewHolder,
        onBindViewHolder = { perCountryDailyGraphItemViewHolder, _, perCountryDailyGraphItem ->
            perCountryDailyGraphItemViewHolder.binding.root.setOnClickListener {
                onItemClick?.invoke(perCountryDailyGraphItem, it)
            }
        }
    )
    register(
        layoutResource = R.layout.item_daily_percountry,
        viewHolder = ::PerCountryDailyItemViewHolder,
        onBindViewHolder = { perCountryDailyItemViewHolder, _, perCountryDailyItem ->
            perCountryDailyItemViewHolder.binding.root.setOnClickListener {
                onItemClick?.invoke(perCountryDailyItem, it)
            }
        }
    )
    register(
        layoutResource = R.layout.item_per_country,
        viewHolder = ::PerCountryViewHolder,
        onBindViewHolder = { perCountryViewHolder, _, perCountryItem ->
            perCountryViewHolder.binding.root.setOnClickListener {
                onItemClick?.invoke(perCountryItem, it)
            }
        }
    )
    register(
        layoutResource = R.layout.item_province_percountry_graph,
        viewHolder = ::PerCountryProvinceGraphItemViewHolder,
        onBindViewHolder = { perCountryProvinceGraphItemViewHolder, _, perCountryProvinceGraphItem ->
            perCountryProvinceGraphItemViewHolder.binding.root.setOnClickListener {
                onItemClick?.invoke(perCountryProvinceGraphItem, it)
            }
        }
    )
    register(
        layoutResource = R.layout.item_pinned,
        viewHolder = ::PinnedItemViewHolder
    )
    register(
        layoutResource = R.layout.item_text,
        viewHolder = ::TextItemViewHolder,
        onBindViewHolder = { textItemViewHolder, _, textItem ->
            textItemViewHolder.binding.textAction.setOnClickListener {
                onItemClick?.invoke(textItem, it)
            }
        }
    )
    register(
        layoutResource = R.layout.continent_item_view,
        viewHolder = ::ContinentItemViewHolder,
        onBindViewHolder = {continentItemViewHolder, _, continentItem ->
            continentItemViewHolder.binding.root.setOnClickListener {
                onItemClick?.invoke(continentItem, it)
            }
        }
    )
}