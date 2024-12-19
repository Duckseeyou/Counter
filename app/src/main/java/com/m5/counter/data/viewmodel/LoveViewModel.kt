package com.m5.counter.data.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.m5.counter.Constants
import com.m5.counter.data.model.LoveModel
import com.m5.counter.data.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class LoveViewModel : ViewModel() {

    val data = MutableLiveData<LoveModel?>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun onCalculateClick(firstName: String, secondName: String) {
        loading.value = true
        RetrofitInstance.apiService.getPercentage(
            firstName = firstName,
            secondName = secondName,
            apiKey = Constants.API_KEY,
            apiHost = Constants.API_HOST
        ).enqueue(object : retrofit2.Callback<LoveModel> {
            override fun onResponse(
                call: Call<LoveModel>,
                response: Response<LoveModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val loveModel = response.body()!!
                    data.value = loveModel
                } else {
                    error.value = response.message()
                }
                loading.value = false
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                error.value = t.message
                loading.value = false
            }
        })

    }
}