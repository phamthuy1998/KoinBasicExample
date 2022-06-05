package com.thuypham.ptithcm.simplebaseapp.ui.fragment

import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseFragment
import com.thuypham.ptithcm.simplebaseapp.databinding.FragmentLoginBinding
import com.thuypham.ptithcm.simplebaseapp.extension.goBack
import com.thuypham.ptithcm.simplebaseapp.extension.logD
import com.thuypham.ptithcm.simplebaseapp.extension.setOnSingleClickListener
import com.thuypham.ptithcm.simplebaseapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    // Test State ViewModel
    private val loginViewModel: LoginViewModel by viewModels()

    override fun setupToolbar() {
        super.setupToolbar()
        toolbarHelper.setToolbarTitle(R.string.login)
        toolbarHelper.setLeftBtn(R.drawable.ic_back) {
            goBack()
        }
    }

    override fun setupView() {
        binding.apply {
            edtUsername.setText(loginViewModel.userName.value)
            edtPassword.setText(loginViewModel.password.value)

            // On text change
            edtUsername.doOnTextChanged { text, _, _, _ -> loginViewModel.setUsername(text.toString()) }
            edtPassword.doOnTextChanged { text, _, _, _ -> loginViewModel.setPassword(text.toString()) }

            btnLogin.setOnSingleClickListener {
                loginViewModel.login()
            }
        }
    }

    override fun setupDataObserver() {
        super.setupDataObserver()

        loginViewModel.error.observe(viewLifecycleOwner) {
            logD("setupDataObserver: error:$it")
            showSnackBar(it.errorResponse?.statusMessage ?: it.extra)
        }

        loginViewModel.isLoading.observe(viewLifecycleOwner) {
            setLoadingStatus(it)
        }

        loginViewModel.loginResponse.observe(viewLifecycleOwner) {
            goBack()
        }
    }

}