package com.example.workmate_test.presentation.user.details.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.workmate_test.presentation.user.details.tabs.UserContactsFragment
import com.example.workmate_test.presentation.user.details.tabs.UserInfoFragment
import com.example.workmate_test.presentation.user.details.tabs.UserLocationFragment

class DetailsPagerAdapter(
    fragment: Fragment,
    private val userId: String
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserInfoFragment.newInstance(userId)
            1 -> UserContactsFragment.newInstance(userId)
            2 -> UserLocationFragment.newInstance(userId)
            else -> UserInfoFragment.newInstance(userId)
        }
    }
}