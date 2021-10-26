package com.polinema.bukukasmasaldi.ui.detailcashflow

import androidx.recyclerview.widget.DiffUtil
import com.polinema.bukukasmasaldi.data.model.Transaction

class CashFlowDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }

}