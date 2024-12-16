package com.m5.counter.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


import com.m5.counter.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var binding: FragmentResultBinding? = null
    private val args: ResultFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            firstName.text = args.result?.firstName
            secondName.text = args.result?.secondName
            percentage.text = args.result?.percentage + "%"
            result.text = args.result?.result
            tryAgainBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }


    }
}