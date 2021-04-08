package kh.com.wbfinance.vannak.sampleproject1.ui.base.recycler

interface OnItemClickListener {
    fun onHeaderClicked() {}
    fun onItemClicked(position: Int)
    fun onFooterClicked() {}
}