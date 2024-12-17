package com.m5.counter.data.presenter
import com.m5.counter.Constants
import com.m5.counter.data.network.RetrofitInstance
import com.m5.counter.data.model.LoveModel
import com.m5.counter.data.view.LoveView
import retrofit2.Call
import retrofit2.Response

class LovePresenter {
    private var loveView: LoveView? = null

    fun attachView(loveView: LoveView) {
        this.loveView = loveView
    }
    fun detachView() {
        loveView = null
    }

    fun onCalculateClick(firstName: String, secondName: String){
        loveView?.showLoading(true)
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
                    loveView?.showLoading(false)
                    loveView?.showResult(loveModel)
                }
                else{
                    loveView?.showLoading(false)
                    loveView?.showToast("Error")
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                loveView?.showLoading(false)
                loveView?.showToast(t.message.toString())
            }
        })
    }
}