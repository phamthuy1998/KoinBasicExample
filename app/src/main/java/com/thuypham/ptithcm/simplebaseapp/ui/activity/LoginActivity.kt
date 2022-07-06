package com.thuypham.ptithcm.simplebaseapp.ui.activity

import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseActivity
import com.thuypham.ptithcm.simplebaseapp.databinding.ActivityLoginBinding
import com.thuypham.ptithcm.simplebaseapp.di.LOGIN_SCOPE
import com.thuypham.ptithcm.simplebaseapp.di.LOGIN_SCOPE2
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login), AndroidScopeComponent {
    override val scope: Scope = getKoin().getOrCreateScope(LOGIN_SCOPE, named(LOGIN_SCOPE))

    private val scope2: Scope = getKoin().getOrCreateScope(LOGIN_SCOPE2, named(LOGIN_SCOPE2))
    override fun setupLogic() {

    }

    override fun setupView() {
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
        scope2.close()
    }

}