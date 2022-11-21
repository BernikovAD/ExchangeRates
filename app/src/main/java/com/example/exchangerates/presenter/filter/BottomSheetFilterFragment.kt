package com.example.exchangerates.presenter.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.exchangerates.databinding.FragmentFilterBinding
import com.example.picturesoftheday.settings.PrefConfing
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFilterFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBinding


    private var count = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.radioButtonParent.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                0 -> {
                    binding.radioButtonChild.visibility = View.VISIBLE
                    count = 1
                }
                1 -> {
                    binding.radioButtonChild.visibility = View.VISIBLE
                    count = 2
                }
                2 -> {
                    PrefConfing.save(requireContext(), 5)
                    findNavController().navigateUp()
                }
            }
        }
        binding.radioButtonChild.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                0 -> {
                    if (count == 1) {
                        PrefConfing.save(requireContext(), 1)
                    } else if (count == 2) {
                        PrefConfing.save(requireContext(), 3)
                    }
                    findNavController().navigateUp()
                }
                1 -> {
                    if (count == 1) {
                        PrefConfing.save(requireContext(), 2)
                    } else if (count == 2) {
                        PrefConfing.save(requireContext(), 4)
                    }
                    findNavController().navigateUp()
                }
            }
        }
    }
}