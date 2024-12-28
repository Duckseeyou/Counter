package com.m5.counter.ui.fragments



import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.m5.counter.data.viewmodel.LoveViewModel
import com.m5.counter.databinding.FragmentCalculateBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalculateFragment : Fragment(){

    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoveViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateBtn.setOnClickListener {
            viewModel.onCalculateClick(
                binding.firstName.text.toString(),
                binding.secondName.text.toString()
            )
        }
        binding.historyBtn.setOnClickListener {
            findNavController().navigate(CalculateFragmentDirections.actionCalculateFragmentToHistoryFragment())
        }
        viewModel.apiData.observe(viewLifecycleOwner) { loveModel ->
            if (loveModel != null) {
                binding.firstName.text.clear()
                binding.secondName.text.clear()
                findNavController().navigate(
                    CalculateFragmentDirections.actionCalculateFragmentToResultFragment(loveModel)
                )
                viewModel.apiData.value = null
            } else {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.apply {
                    progressIndicator.visibility = View.VISIBLE
                    calculateBtn.visibility = View.GONE
                    historyBtn.visibility = View.GONE
                    firstName.isEnabled = false
                    secondName.isEnabled = false
                    val animation = ObjectAnimator.ofInt(progressIndicator, "progress", 0, 90)
                    animation.interpolator = LinearInterpolator()
                    animation.start()
                    dimContainer.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    firstName.isEnabled = true
                    secondName.isEnabled = true
                    historyBtn.visibility = View.VISIBLE
                    dimContainer.visibility = View.GONE
                    progressIndicator.visibility = View.GONE
                    calculateBtn.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}