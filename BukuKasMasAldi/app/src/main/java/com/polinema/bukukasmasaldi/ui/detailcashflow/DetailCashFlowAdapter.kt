package com.polinema.bukukasmasaldi.ui.detailcashflow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polinema.bukukasmasaldi.R
import com.polinema.bukukasmasaldi.data.model.Transaction
import com.polinema.bukukasmasaldi.databinding.ItemCashFlowBinding
import com.polinema.bukukasmasaldi.utils.toRupiah

class DetailCashFlowAdapter :
    ListAdapter<Transaction, DetailCashFlowAdapter.ViewHolder>(CashFlowDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailCashFlowAdapter.ViewHolder {
        val view = ItemCashFlowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailCashFlowAdapter.ViewHolder, position: Int) {
        val transaction = getItem(position) as Transaction
        holder.bind(transaction)
    }

    inner class ViewHolder(private val binding: ItemCashFlowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {

            binding.apply {
                tvAmountItemCashFlow.text = transaction.amount?.toRupiah()
                tvDateItemCashFlow.text = transaction.date
                tvDescriptionItemCashFlow.text = transaction.description

                if(transaction.isExpanse == 1) {
                    icStatusItemCashFlow.setImageResource(R.drawable.ic_expanse)
                    icStatusItemCashFlow.setColorFilter(ContextCompat.getColor(this.root.context, R.color.red))
                    cardItemCashFlow.strokeColor = ContextCompat.getColor(this.root.context, R.color.red)
                }else {
                    icStatusItemCashFlow.setImageResource(R.drawable.ic_income)
                    icStatusItemCashFlow.setColorFilter(ContextCompat.getColor(this.root.context, R.color.green))
                    cardItemCashFlow.strokeColor = ContextCompat.getColor(this.root.context, R.color.green)
                }
            }

        }

    }

}