package kh.com.wbfinance.vannak.sampleproject1.ui.adapter.viewholders

import android.view.View
import kh.com.wbfinance.vannak.sampleproject1.databinding.ItemErrorStateBinding
import kh.com.wbfinance.vannak.sampleproject1.ui.base.BaseViewItem
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder


class ErrorStateItem(
    val titleResId: Int,
    val subtitleResId: Int
) : BaseViewItem

class ErrorStateItemViewHolder(itemView: View) : RecyclerViewHolder<ErrorStateItem>(itemView) {

    val binding: ItemErrorStateBinding = ItemErrorStateBinding.bind(itemView)

    override fun bind(position: Int, item: ErrorStateItem) {
        super.bind(position, item)
        with(binding) {
            textTitle.text = itemView.context.getString(item.titleResId)
            textSubtitle.text = itemView.context.getString(item.subtitleResId)
        }
    }
}