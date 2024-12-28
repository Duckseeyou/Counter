package com.m5.counter.data.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.m5.counter.Constants
import com.m5.counter.data.local.HistoryDao
import com.m5.counter.data.network.ApiService
import com.m5.counter.data.model.LoveModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoveViewModel @Inject constructor(private val apiService: ApiService, private val historyDao: HistoryDao) : ViewModel() {
    val apiData = MutableLiveData<LoveModel?>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun onCalculateClick(firstName: String, secondName: String) {
        loading.value = true
        apiService.getPercentage(
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
                    historyDao.insert(loveModel)
                    apiData.value = loveModel
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

    fun getHistory(): LiveData<List<LoveModel>> {
        return historyDao.getAll()
    }
    fun deleteAll() {
        historyDao.deleteAll()
    }
}