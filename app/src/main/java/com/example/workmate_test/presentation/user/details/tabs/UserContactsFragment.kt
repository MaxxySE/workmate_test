package com.example.workmate_test.presentation.user.details.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.workmate_test.R
import com.example.workmate_test.databinding.FragmentUserContactsBinding
import com.example.workmate_test.presentation.user.details.DetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserContactsFragment : Fragment(R.layout.fragment_user_contacts) {

    private lateinit var binding: FragmentUserContactsBinding
    private val viewModel: DetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserContactsBinding.bind(view)

        val userId = arguments?.getString("userId") ?: return
        viewModel.loadUser(userId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { user ->
                    user?.let {
                        binding.tvPhone.text = "Phone: ${it.phone}"
                        binding.tvEmail.text = "Email: ${it.email}"
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(userId: String) = UserContactsFragment().apply {
            arguments = Bundle().apply { putString("userId", userId) }
        }
    }
}