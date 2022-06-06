package com.thuypham.ptithcm.simplebaseapp.util

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.thuypham.ptithcm.simplebaseapp.R
import com.thuypham.ptithcm.simplebaseapp.extension.setOnSingleClickListener
import com.thuypham.ptithcm.simplebaseapp.extension.show

class ToolbarHelper(private val view: View) {

    fun setToolbarTitle(title: String, onClick: ((View) -> Unit?)? = null) {
        view.findViewById<AppCompatTextView>(R.id.tvTitle)?.apply {
            show()
            text = title
            isSelected = true
            setOnSingleClickListener { onClick?.invoke(this) }
        }
    }

    fun setToolbarTitle(titleRes: Int, onClick: ((View) -> Unit?)? = null) {
        view.findViewById<AppCompatTextView>(R.id.tvTitle)?.apply {
            show()
            text = view.context.getString(titleRes)
            setOnSingleClickListener { onClick?.invoke(this) }
        }
    }

    fun setLeftBtn(iconResID: Int, onClick: ((View) -> Unit?)? = null) {
        view.findViewById<AppCompatImageView>(R.id.ivLeft)?.apply {
            show()
            setImageResource(iconResID)
            setOnSingleClickListener { onClick?.invoke(this) }
        }
    }

    fun setRightBtn(iconResID: Int, onClick: ((View) -> Unit?)? = null) {
        view.findViewById<AppCompatImageView>(R.id.ivRight)?.apply {
            show()
            setImageResource(iconResID)
            setOnSingleClickListener { onClick?.invoke(this) }
        }
    }

    fun setSubRightBtn(iconResID: Int, onClick: ((View) -> Unit?)? = null) {
        view.findViewById<AppCompatImageView>(R.id.ivSubRight)?.apply {
            show()
            setImageResource(iconResID)
            setOnSingleClickListener { onClick?.invoke(this) }
        }
    }

    fun setSubRight2Btn(iconResID: Int, onClick: ((View) -> Unit?)? = null) {
        view.findViewById<AppCompatImageView>(R.id.ivSubRight2)?.apply {
            show()
            setImageResource(iconResID)
            setOnSingleClickListener { onClick?.invoke(this) }
        }
    }

}