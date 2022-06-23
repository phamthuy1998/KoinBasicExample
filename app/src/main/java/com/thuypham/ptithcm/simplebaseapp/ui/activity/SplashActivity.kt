package com.thuypham.ptithcm.simplebaseapp.ui.activity

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseActivity
import com.thuypham.ptithcm.simplebaseapp.databinding.ActivitySplashBinding
import com.thuypham.ptithcm.simplebaseapp.di.SPLASH_SCREEN_SCOPE
import com.thuypham.ptithcm.simplebaseapp.viewmodel.SplashViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash), AndroidScopeComponent {

    override val scope: Scope by lazy { getKoin().getOrCreateScope<SplashActivity>(SplashActivity::class.java.name) }
    private val splashViewModel: SplashViewModel by viewModel()

    private val animationListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(p0: Animator?) {}

        override fun onAnimationEnd(p0: Animator?) {
            val intent = if (splashViewModel.isUserLogin()) {
                Intent(this@SplashActivity, MainActivity::class.java)
            } else {
                Intent(this@SplashActivity, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }

        override fun onAnimationCancel(p0: Animator?) {}

        override fun onAnimationRepeat(p0: Animator?) {}
    }

    override fun setupView() {
        binding.animationView.addAnimatorListener(animationListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.animationView.removeAllAnimatorListeners()

        // don't forget to close current scope in needed
        scope.close()
    }

}