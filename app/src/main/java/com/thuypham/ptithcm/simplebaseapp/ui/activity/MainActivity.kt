package com.thuypham.ptithcm.simplebaseapp.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseActivity
import com.thuypham.ptithcm.simplebaseapp.databinding.ActivityMainBinding
import com.thuypham.ptithcm.simplebaseapp.di.*
import com.thuypham.ptithcm.simplebaseapp.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.logger.AndroidLogger
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun setupView() {

    }

    override fun setupDataObserver() {
        super.setupDataObserver()

    }

}