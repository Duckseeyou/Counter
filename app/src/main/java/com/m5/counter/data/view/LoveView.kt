package com.m5.counter.data.view
import com.m5.counter.data.model.LoveModel

interface LoveView {
    fun showLoading(isLoading: Boolean)
    fun showResult(loveModel: LoveModel)
    fun showToast(message: String)
}