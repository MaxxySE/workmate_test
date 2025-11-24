package com.example.workmate_test.presentation.user.details

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.workmate_test.R
import com.example.workmate_test.databinding.FragmentDetailsBinding
import com.example.workmate_test.presentation.user.details.adapter.DetailsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        val userId = arguments?.getString("userId")

        if (userId != null) {
            viewModel.loadUser(userId)
            setupTabs(userId)
        }

        buttonsListeners()
        observeUser()
    }

    private fun buttonsListeners() {

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun setupTabs(userId: String) {
        val adapter = DetailsPagerAdapter(this, userId)
        binding.pager.adapter = adapter

        val icons = listOf(
            R.drawable.person,
            R.drawable.phone,
            R.drawable.location
        )

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.icon = ContextCompat.getDrawable(requireContext(), icons[position])
            tab.text = null
        }.attach()
    }

    private fun observeUser() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { user ->
                    user?.let { fillUI(it) }
                }
            }
        }
    }

    private fun fillUI(user: com.example.workmate_test.domain.model.User) {
        binding.apply {
            name.text = "${user.firstName} ${user.lastName}"

            Glide.with(root)
                .load(user.photoUrl)
                .placeholder(R.color.gray)
                .transform(CircleCrop())
                .into(profilePicture)
        }
    }
}