package com.polinema.bukukasmasaldi.ui.settings

import android.content.ContentValues
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.polinema.bukukasmasaldi.R
import com.polinema.bukukasmasaldi.data.db.DatabaseContract
import com.polinema.bukukasmasaldi.data.model.User
import com.polinema.bukukasmasaldi.databinding.ActivitySettingBinding
import com.polinema.bukukasmasaldi.utils.hideKeyboard
import com.polinema.bukukasmasaldi.utils.loadAsCircleFromDrawable
import com.polinema.bukukasmasaldi.utils.snack
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingActivity : AppCompatActivity() {

    private val binding: ActivitySettingBinding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }

    private val viewModel: SettingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSettings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getCurrentUser()
        viewModel.currentUser.observe(this, {
            setupUI(it)
        })

        viewModel.editSuccess.observe(this, {
            if (it) {
                binding.root.snack("Berhasil merubah password")
                viewModel.getCurrentUser()
            } else {
                binding.root.snack("Gagal merubah password, silahkan coba lagi")
            }
        })

    }

    private fun setupUI(currentUser: User) {
        binding.imgAuthor.loadAsCircleFromDrawable(R.drawable.img_author)

        binding.btnSavePassword.setOnClickListener {

            val newPassword = binding.edtNewPassword.editText?.text.toString()
            val currentPassword = binding.edtCurrentPassword.editText?.text.toString()
            val validCurrentPassword = currentUser.password.toString()

            val isValid = validateInput(validCurrentPassword, currentPassword, newPassword)

            if (isValid) {
                val newUserValues = ContentValues()
                newUserValues.put(DatabaseContract.UserColumns._ID, 1)
                newUserValues.put(DatabaseContract.UserColumns.USERNAME, "user")
                newUserValues.put(DatabaseContract.UserColumns.PASSWORD, newPassword)

                viewModel.editPassword(newUserValues)
                hideKeyboard(it)
            }
        }
    }

    private fun validateInput(validCurrentPassword: String, currentPassword: String, password: String) =
        when {
            currentPassword != validCurrentPassword -> {
                binding.edtCurrentPassword.apply {
                    isErrorEnabled = true
                    error = resources.getString(R.string.failed_edit_current_password)
                    requestFocus()
                }
                false
            }
            currentPassword.isEmpty() -> {
                binding.edtCurrentPassword.apply {
                    isErrorEnabled = true
                    error = resources.getString(R.string.empty_password_message)
                    requestFocus()
                }
                false
            }
            password.isEmpty() -> {
                binding.edtNewPassword.apply {
                    isErrorEnabled = true
                    error = resources.getString(R.string.empty_password_message)
                    requestFocus()
                }
                false
            }
            password == currentPassword -> {
                binding.edtNewPassword.apply {
                    isErrorEnabled = true
                    error = resources.getString(R.string.failed_edit_password)
                    requestFocus()
                }
                false
            }
            else -> {
                binding.apply {
                    edtNewPassword.clearFocus()
                    edtNewPassword.isErrorEnabled = false
                }
                true
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return false
    }
}