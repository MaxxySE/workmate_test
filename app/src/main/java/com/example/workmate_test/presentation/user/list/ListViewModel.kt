package com.example.workmate_test.presentation.user.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workmate_test.domain.model.User
import com.example.workmate_test.domain.usecase.GetUserListUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListViewModel(
    getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    val users: StateFlow<List<User>> = getUserListUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}