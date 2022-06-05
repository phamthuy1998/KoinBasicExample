package com.thuypham.ptithcm.simplebaseapp.ui.activity

import android.content.Context
import android.content.Intent
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.base.BaseActivity
import com.thuypham.ptithcm.simplebaseapp.databinding.ActivityNoNetworkBinding
import com.thuypham.ptithcm.simplebaseapp.extension.isNetworkConnected
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoNetworkActivity : BaseActivity<ActivityNoNetworkBinding>(R.layout.activity_no_network) {

    companion object {
        var isShow = false
        fun start(context: Context?) {
            if (!isShow)
                context?.let {
                    val intent = Intent(it, NoNetworkActivity::class.java)
                    it.startActivity(intent)
                }
            isShow = true
        }
    }

    override fun setupView() {

    }

    override fun onNetworkAvailable() {
        super.onNetworkAvailable()
        finish()
    }

    override fun onBackPressed() {
        if (isNetworkConnected()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isShow = false
    }
}