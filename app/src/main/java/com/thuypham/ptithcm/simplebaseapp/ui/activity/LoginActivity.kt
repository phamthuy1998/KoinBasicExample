package com.thuypham.ptithcm.simplebaseapp.ui.activity

import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseActivity
import com.thuypham.ptithcm.simplebaseapp.databinding.ActivityLoginBinding
import com.thuypham.ptithcm.simplebaseapp.di.LOGIN_SCOPE
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login), AndroidScopeComponent {
    override val scope: Scope by lazy { getKoin().getOrCreateScope(LOGIN_SCOPE, named(LOGIN_SCOPE)) }

    override fun setupLogic() {

    }

    override fun setupView() {
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }

}