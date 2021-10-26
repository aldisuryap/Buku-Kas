package com.polinema.bukukasmasaldi.utils

import com.polinema.bukukasmasaldi.data.model.Transaction

object TransactionSeparator {
    fun getSumExpanse(transactions: List<Transaction>): Int {
        var expanse = 0

        for (item in transactions) {
            if (item.isExpanse == 1) {
                item.amount?.let {
                    expanse += it
                }
            }
        }

        return expanse
    }

    fun getSumIncome(transactions: List<Transaction>): Int {
        var income = 0

        for (item in transactions) {
            if (item.isExpanse == 0) {
                item.amount?.let {
                    income += it
                }
            }
        }

        return income
    }

    fun getAllDate(transactions: List<Transaction>): List<String> {
        val listDate = ArrayList<String>()

        for (transaction in transactions) {
            listDate.add(transaction.date.toString())
        }

        return listDate.distinct().reversed()
    }

    fun getTotalIncomeByDate(transactions: List<Transaction>, listDate: List<String>): List<Int> {
        val listTotal = ArrayList<Int>()

        for (date in listDate) {
            var total = 0
            for (transaction in transactions) {
                if (transaction.date == date && transaction.isExpanse == 0) {
                    total += transaction.amount ?: 0

                }
            }
            listTotal.add(total)
        }

        return listTotal.reversed()
    }

    fun getTotalExpanseByDate(transactions: List<Transaction>, listDate: List<String>): List<Int> {
        val listTotal = ArrayList<Int>()

        for (date in listDate) {
            var total = 0
            for (transaction in transactions) {
                if (transaction.date == date && transaction.isExpanse == 1) {
                    total += transaction.amount ?: 0

                }
            }
            listTotal.add(total)
        }

        return listTotal.reversed()
    }
}