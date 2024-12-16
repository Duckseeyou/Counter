package com.m5.counter.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.m5.counter.Constants
import com.m5.counter.data.PercentageResponse
import com.m5.counter.data.RetrofitInstance
import com.m5.counter.databinding.FragmentCalculateBinding
import retrofit2.Call
import retrofit2.Response


class CalculateFragment : Fragment() {

    private var binding: FragmentCalculateBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.calculateBtn?.setOnClickListener {
            RetrofitInstance.apiService.getPercentage(
                firstName = binding?.firstName?.text.toString(),
                secondName = binding?.secondName?.text.toString(),
                apiKey = Constants.API_KEY,
                apiHost = Constants.API_HOST
            ).enqueue(object : retrofit2.Callback<PercentageResponse> {
                override fun onResponse(
                    call: Call<PercentageResponse>,
                    response: Response<PercentageResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        binding?.firstName?.text?.clear()
                        binding?.secondName?.text?.clear()
                        findNavController().navigate(CalculateFragmentDirections.actionCalculateFragmentToResultFragment(
                            response.body()!!
                        ))
                    }
                }

                override fun onFailure(call: Call<PercentageResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}