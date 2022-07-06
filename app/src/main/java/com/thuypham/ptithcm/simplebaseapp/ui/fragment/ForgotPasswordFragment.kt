package com.thuypham.ptithcm.simplebaseapp.ui.fragment

import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseFragment
import com.thuypham.ptithcm.simplebaseapp.databinding.FragmentForgotPasswordBinding
import com.thuypham.ptithcm.simplebaseapp.di.LOGIN_SCOPE
import com.thuypham.ptithcm.simplebaseapp.di.LOGIN_SCOPE2
import com.thuypham.ptithcm.simplebaseapp.extension.goBack
import com.thuypham.ptithcm.simplebaseapp.extension.logD
import com.thuypham.ptithcm.simplebaseapp.extension.sharedViewModel1
import com.thuypham.ptithcm.simplebaseapp.viewmodel.ForgotPasswordViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.LoginViewModel
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MultiScopeViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>(R.layout.fragment_forgot_password), AndroidScopeComponent {

    override val scope: Scope by lazy { getKoin().getScope(LOGIN_SCOPE) }

    // Test scope shared view model
    private val forgotPasswordViewModel: ForgotPasswordViewModel by sharedViewModel()

    private val scope2 by lazy { getKoin().getScope(LOGIN_SCOPE2) }
    private val multiScopeViewModel: MultiScopeViewModel by sharedViewModel1(scope = scope2)

    override fun setupView() {

        binding.run {
            edtUsername.setText(forgotPasswordViewModel.username)
        }
        logD("multiScopeViewModel: ${multiScopeViewModel.message}")
    }

    override fun setupToolbar() {
        setLeftBtn(R.drawable.ic_back) {
            goBack()
        }
        setToolbarTitle(R.string.forgot_password)
    }
}