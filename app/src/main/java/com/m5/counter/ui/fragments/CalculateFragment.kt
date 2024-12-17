package com.m5.counter.ui.fragments



import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.m5.counter.data.model.LoveModel
import com.m5.counter.data.presenter.LovePresenter
import com.m5.counter.data.view.LoveView
import com.m5.counter.databinding.FragmentCalculateBinding


class CalculateFragment : Fragment(), LoveView {

    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!
    private val presenter = LovePresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        binding.calculateBtn.setOnClickListener {
            presenter.onCalculateClick(binding.firstName.text.toString(), binding.secondName.text.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.detachView()
    }

    override fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.apply {
                progressIndicator.visibility = View.VISIBLE
                calculateBtn.visibility = View.GONE
                val animation = ObjectAnimator.ofInt(progressIndicator, "progress", 0, 100)
                animation.setDuration(300) // 0.5 second
                animation.interpolator = LinearInterpolator()
                animation.start()
                dimContainer.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                dimContainer.visibility = View.GONE
                progressIndicator.visibility = View.GONE
                calculateBtn.visibility = View.VISIBLE
            }
        }
    }

    override fun showResult(loveModel: LoveModel) {
        binding.firstName.text.clear()
        binding.secondName.text.clear()
        findNavController().navigate(CalculateFragmentDirections.actionCalculateFragmentToResultFragment(loveModel))
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}