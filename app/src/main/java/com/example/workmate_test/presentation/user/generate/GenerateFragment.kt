package com.example.workmate_test.presentation.user.generate

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.workmate_test.R
import com.example.workmate_test.databinding.FragmentGenerateBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateFragment : Fragment(R.layout.fragment_generate) {

    private lateinit var binding: FragmentGenerateBinding

    private val viewModel: GenerateViewModel by viewModel()

    private val gender: ArrayList<String> = arrayListOf("Male", "Female")
    private val nationalities: ArrayList<String> =
        arrayListOf("AU", "BR", "CA", "CH", "DE", "DK", "ES", "FI", "FR", "GB",
            "IE", "IN", "IR", "MX", "NL", "NO", "NZ", "RS", "TR", "UA", "US")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGenerateBinding.bind(view)

        val isRoot = findNavController().previousBackStackEntry == null
        binding.backButton.isVisible = !isRoot

        setupSpinners()
        setupListeners()
        observeState()
    }

    private fun setupSpinners() {
        setSpinner(gender, binding.genderSpinner)
        setSpinner(nationalities, binding.nationalitySpinner)
    }

    private fun setupListeners() {
        binding.generateButton.setOnClickListener {
            val selectedGender = binding.genderSpinner.selectedItem.toString()
            val selectedNat = binding.nationalitySpinner.selectedItem.toString()

            viewModel.onGenerateClicked(gender = selectedGender, nat = selectedNat)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.state.collect { state ->
                    when (state) {
                        is GenerateState.Initial -> {
                            binding.generateButton.isEnabled = true
                            binding.generateButton.text = getString(R.string.generate_button)
                        }
                        is GenerateState.Loading -> {
                            binding.generateButton.isEnabled = false
                            binding.generateButton.text = "Loading..."
                        }
                        is GenerateState.Success -> {
                            binding.generateButton.isEnabled = true
                            binding.generateButton.text = getString(R.string.generate_button)

                            findNavController().navigate(R.id.action_generateFragment_to_listFragment)
                        }
                        is GenerateState.Error -> {
                            binding.generateButton.isEnabled = true
                            binding.generateButton.text = getString(R.string.generate_button)
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()

                            findNavController().navigate(R.id.action_generateFragment_to_listFragment)
                        }
                    }
                }
            }
        }
    }

    private fun setSpinner(dataList: ArrayList<String>, spinner: Spinner) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, dataList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}