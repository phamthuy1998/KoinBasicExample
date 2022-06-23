package com.thuypham.ptithcm.simplebaseapp.ui.fragment

import android.content.Intent
import androidx.core.widget.doOnTextChanged
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseFragment
import com.thuypham.ptithcm.simplebaseapp.databinding.FragmentLoginBinding
import com.thuypham.ptithcm.simplebaseapp.di.LOGIN_SCOPE
import com.thuypham.ptithcm.simplebaseapp.extension.logD
import com.thuypham.ptithcm.simplebaseapp.extension.setOnSingleClickListener
import com.thuypham.ptithcm.simplebaseapp.ui.activity.MainActivity
import com.thuypham.ptithcm.simplebaseapp.viewmodel.LoginViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.context.stopKoin
import org.koin.core.scope.Scope

/* For Testing stateViewModel injection */
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login), AndroidScopeComponent {

    override val scope: Scope by lazy { getKoin().getScope(LOGIN_SCOPE) }
    private val loginViewModel: LoginViewModel by stateViewModel()

    override fun setupToolbar() {
        super.setupToolbar()
        setToolbarTitle(R.string.login)
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
            startHomeActivity()
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}