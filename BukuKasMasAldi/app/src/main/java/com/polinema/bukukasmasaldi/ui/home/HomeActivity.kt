package com.polinema.bukukasmasaldi.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.polinema.bukukasmasaldi.R
import com.polinema.bukukasmasaldi.databinding.ActivityHomeBinding
import com.polinema.bukukasmasaldi.ui.addtransaction.AddTransactionBottomSheet
import com.polinema.bukukasmasaldi.ui.detailcashflow.DetailCashFlowActivity
import com.polinema.bukukasmasaldi.ui.settings.SettingActivity
import com.polinema.bukukasmasaldi.utils.TransactionSeparator
import com.polinema.bukukasmasaldi.utils.toRupiah
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getCurrentUsername()
        viewModel.getAllTransaction()

        viewModel.currentUsername.observe(this, {
            val greetingMessage = String.format(resources.getString(R.string.greeting_message), it)
            binding.tvGreeting.text = greetingMessage
        })

        viewModel.allTransaction.observe(this, {
            val income = TransactionSeparator.getSumIncome(it)
            val expanse = TransactionSeparator.getSumExpanse(it)

            binding.cardOverviewIncome.tvAmountCardOverview.text = income.toRupiah()
            binding.cardOverviewExpanse.tvAmountCardOverview.text = expanse.toRupiah()
        })

        setupUI()
    }

    private fun onDismissCallback() {
        viewModel.getAllTransaction()
    }

    private fun setupUI() {
        binding.apply {
            cardOverviewIncome.tvTitleCardOverview.text = resources.getString(R.string.title_income)
            cardOverviewIncome.iconCardOverview.setImageResource(R.drawable.ic_income)

            cardOverviewExpanse.tvTitleCardOverview.text =
                resources.getString(R.string.title_expanse)
            cardOverviewExpanse.iconCardOverview.setImageResource(R.drawable.ic_expanse)

            btnAddIncome.setOnClickListener {
                val addIncomeBottomSheet =
                    AddTransactionBottomSheet(::onDismissCallback, isExpanse = false)
                addIncomeBottomSheet.show(supportFragmentManager, TAG_SHEET_INCOME)
            }

            btnAddExpanse.setOnClickListener {
                val addExpanseBottomSheet =
                    AddTransactionBottomSheet(::onDismissCallback, isExpanse = true)
                addExpanseBottomSheet.show(supportFragmentManager, TAG_SHEET_EXPANSE)
            }

            btnDetailCashFlow.setOnClickListener {
                startActivity(Intent(this@HomeActivity, DetailCashFlowActivity::class.java))
            }

            btnSettings.setOnClickListener {
                startActivity(Intent(this@HomeActivity, SettingActivity::class.java))
            }
        }
    }

    companion object {
        private const val TAG_SHEET_INCOME = "income"
        private const val TAG_SHEET_EXPANSE = "expanse"
    }
}