package com.example.workmate_test.presentation.user.details.tabs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.workmate_test.R
import com.example.workmate_test.databinding.FragmentUserInfoBinding // Binding сгенерировался сам
import com.example.workmate_test.presentation.user.details.DetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoFragment : Fragment(R.layout.fragment_user_info) {

    private lateinit var binding: FragmentUserInfoBinding
    private val viewModel: DetailsViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserInfoBinding.bind(view)

        val userId = arguments?.getString("userId") ?: return

        viewModel.loadUser(userId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { user ->
                    user?.let {
                        binding.tvGender.text = "Gender: ${it.gender}"
                        binding.tvAge.text = "Age: ${it.age}"
                        binding.tvBirth.text = "Birth: ${it.birthDate}"
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(userId: String) = UserInfoFragment().apply {
            arguments = Bundle().apply { putString("userId", userId) }
        }
    }
}