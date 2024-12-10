package com.m5.counter

interface CounterView {
    fun updateCounter(count: Int, color: String)
    fun showToast(message: String)
}