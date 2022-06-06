package com.thuypham.ptithcm.simplebaseapp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.thuypham.ptithcm.simplebaseapp.extension.logD
import com.thuypham.ptithcm.simplebaseapp.util.ToolbarHelper

abstract class BaseFragment<T : ViewDataBinding>(private val layoutId: Int) : Fragment() {

    lateinit var binding: T
    protected val toolbarHelper by lazy { ToolbarHelper(binding.root) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logD("onCreateView")
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logD("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
        setupLogic()
        getData()
        setupView()
        setupDataObserver()
    }

    open fun setupLogic() {}
    open fun getData() {}
    open fun setupToolbar() {}
    abstract fun setupView()
    open fun setupDataObserver() {}

    fun showLoading() {
        (requireActivity() as BaseActivity<*>).showLoading()
    }

    fun setLoadingStatus(isLoading: Boolean) {
        (requireActivity() as BaseActivity<*>).setLoadingStatus(isLoading)
    }

    fun hideLoading() {
        (requireActivity() as BaseActivity<*>).hideLoading()
    }

    fun runOnUiThread(runnable: Runnable?) {
        if (activity == null || !isAdded) {
            return
        }
        activity?.runOnUiThread(runnable)
    }

    protected fun showSnackBar(resMessage: Int) {
        runOnUiThread(Runnable {
            if (view != null) {
                val snackBar = Snackbar.make(
                    view ?: return@Runnable, (activity
                        ?: return@Runnable).getString(resMessage), Snackbar.LENGTH_LONG
                )
                snackBar.show()
            }
        })
    }

    fun showSnackBar(message: String?) {
        if (message.isNullOrBlank()) return
        runOnUiThread(Runnable {
            if (view != null) {
                val snackBar = Snackbar.make(
                    view ?: return@Runnable, message
                        ?: return@Runnable, Snackbar.LENGTH_SHORT
                )
                snackBar.show()
            }
        })
    }

    protected fun hideKeyboard() {
        if (activity == null) {
            return
        }
        val view = activity?.currentFocus
        if (view != null) {
            (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD("onCreate")
    }

    override fun onStart() {
        super.onStart()
        logD("onStart")
    }

    override fun onResume() {
        super.onResume()
        logD("onResume")
        setupToolbar()
    }

    override fun onPause() {
        super.onPause()
        logD("onPause")
    }

    override fun onStop() {
        super.onStop()
        logD("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logD("onDestroyView")
        hideKeyboard()
    }

    override fun onDestroy() {
        super.onDestroy()
        logD("onDestroy")
    }

}