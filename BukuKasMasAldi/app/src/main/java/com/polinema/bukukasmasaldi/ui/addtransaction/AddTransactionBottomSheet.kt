package com.polinema.bukukasmasaldi.ui.addtransaction

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.polinema.bukukasmasaldi.R
import com.polinema.bukukasmasaldi.data.db.DatabaseContract
import com.polinema.bukukasmasaldi.databinding.BottomSheetAddTransactionBinding
import com.polinema.bukukasmasaldi.utils.DatePickerHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTransactionBottomSheet(
    private val onDismissCallback: () -> Unit,
    private val isExpanse: Boolean
) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetAddTransactionBinding? = null
    private val binding get() = _binding

    private val viewModel: AddTransactionViewModel by viewModel()

    private val datePicker by lazy {
        DatePickerHelper.getDatePicker()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetAddTransactionBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolsTitleTopBottomSheet?.text =
            if (isExpanse) "Tambah Pengeluaran" else "Tambah Pemasukkan"

        viewModel.insertSuccess.observe(viewLifecycleOwner, {
            if (it) {
                onDismissCallback()
                dismiss()
            }
        })

        binding?.edtDateAddIncome?.setEndIconOnClickListener {
            activity?.supportFragmentManager?.let {
                datePicker.show(it, DATE_PICKER_TAG)
            }
        }

        binding?.edtDateAddIncome?.setErrorIconOnClickListener {
            activity?.supportFragmentManager?.let {
                datePicker.show(it, DATE_PICKER_TAG)
            }
        }

        datePicker.addOnPositiveButtonClickListener {
            binding?.edtDateAddIncome?.editText?.setText(datePicker.headerText)
        }

        binding?.btnSaveAddIncome?.setOnClickListener {
            val date = binding?.edtDateAddIncome?.editText?.text.toString()

            val amount = try {
                binding?.edtAmountAddIncome?.editText?.text.toString().toInt()
            } catch (e: NumberFormatException) {
                0
            }

            val description = binding?.edtDescriptionAddIncome?.editText?.text.toString()

            val isValid = validateInput(date, amount, description)

            if (isValid) {
                val incomeValues = ContentValues()
                incomeValues.put(DatabaseContract.TransactionColumns.DATE, date)
                incomeValues.put(DatabaseContract.TransactionColumns.AMOUNT, amount)
                incomeValues.put(DatabaseContract.TransactionColumns.DESCRIPTION, description)

                if (isExpanse) {
                    incomeValues.put(DatabaseContract.TransactionColumns.IS_EXPANSE, true)
                } else {
                    incomeValues.put(DatabaseContract.TransactionColumns.IS_EXPANSE, false)
                }

                viewModel.insert(incomeValues)
            }
        }

    }

    private fun validateInput(date: String, amount: Int, description: String): Boolean =
        when {
            date.isEmpty() -> {
                binding?.edtDateAddIncome?.apply {
                    isErrorEnabled = true
                    error = resources.getString(R.string.empty_date_message)
                    requestFocus()
                }
                false
            }

            amount == 0 -> {
                binding?.edtAmountAddIncome?.apply {
                    isErrorEnabled = true
                    error = resources.getString(R.string.empty_amount_message)
                    requestFocus()
                }
                false
            }

            description.isEmpty() -> {
                binding?.edtDescriptionAddIncome?.apply {
                    isErrorEnabled = true
                    error = resources.getString(R.string.empty_description_message)
                    requestFocus()
                }
                false
            }

            else -> {
                binding?.apply {
                    edtDateAddIncome.clearFocus()
                    edtDateAddIncome.isErrorEnabled = false
                    edtAmountAddIncome.clearFocus()
                    edtAmountAddIncome.isErrorEnabled = false
                    edtDescriptionAddIncome.clearFocus()
                    edtDescriptionAddIncome.isErrorEnabled = false
                }
                true
            }
        }

    companion object {
        private const val DATE_PICKER_TAG = "date_picker_tag"
    }

}