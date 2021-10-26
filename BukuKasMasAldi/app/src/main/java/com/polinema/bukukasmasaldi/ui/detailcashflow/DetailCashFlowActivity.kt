package com.polinema.bukukasmasaldi.ui.detailcashflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.polinema.bukukasmasaldi.databinding.ActivityDetailCashFlowBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCashFlowActivity : AppCompatActivity() {

    private val binding: ActivityDetailCashFlowBinding by lazy {
        ActivityDetailCashFlowBinding.inflate(layoutInflater)
    }

    private val cashFlowAdapter: DetailCashFlowAdapter by lazy {
        DetailCashFlowAdapter()
    }

    private val viewModel: DetailCashFlowViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetailCashFlow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding.rvCashFlow) {
            layoutManager = LinearLayoutManager(this.context)
            adapter = cashFlowAdapter
        }

        viewModel.getAllTransaction()
        viewModel.allTransaction.observe(this, {
            cashFlowAdapter.submitList(it)
        })

        binding.btnBack.setOnClickListener { finish() }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return false
    }
}