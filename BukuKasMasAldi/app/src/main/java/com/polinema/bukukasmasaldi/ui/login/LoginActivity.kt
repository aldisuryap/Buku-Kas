package com.polinema.bukukasmasaldi.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.polinema.bukukasmasaldi.R
import com.polinema.bukukasmasaldi.databinding.ActivityLoginBinding
import com.polinema.bukukasmasaldi.ui.home.HomeActivity
import com.polinema.bukukasmasaldi.utils.UserGenerator
import com.polinema.bukukasmasaldi.utils.hideKeyboard
import com.polinema.bukukasmasaldi.utils.snack
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.isFirstTime.observe(this, { isFirstTime ->
            if (isFirstTime) {
                val validUser = UserGenerator.getFirstUser()
                viewModel.saveUser(validUser)
                viewModel.setIsFirstTime(false)
            }
        })

        viewModel.isLoginSuccess.observe(this, { isLoginSuccess ->
            if (isLoginSuccess) {
                startActivity(Intent(this, HomeActivity::class.java)).also { finish() }
            } else {
                binding.root.snack(resources.getString(R.string.login_failed_message))
            }
        })

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.editText?.text.toString()
            val password = binding.edtPassword.editText?.text.toString()

            val isValid = validateInput(username, password)

            if (isValid) {
                viewModel.doLogin(username, password)
            }

            hideKeyboard(it)

            return@setOnClickListener
        }

    }

    private fun validateInput(username: String, password: String) =
        when {
            username.isEmpty() -> {
                binding.edtUsername.apply {
                    isErrorEnabled = true
                    error = resources.getString(R.string.empty_username_message)
                    requestFocus()
                }
                false
            }
            password.isEmpty() -> {
                binding.edtPassword.apply {
                    isErrorEnabled = true
                    error = resources.getString(R.string.empty_password_message)
                    requestFocus()
                }
                false
            }
            else -> {
                binding.apply {
                    edtUsername.clearFocus()
                    edtUsername.isErrorEnabled = false
                    edtPassword.clearFocus()
                    edtPassword.isErrorEnabled = false
                }
                true
            }
        }
}