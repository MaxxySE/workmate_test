package com.example.workmate_test.presentation.user.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workmate_test.domain.usecase.GenerateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GenerateViewModel(
    private val generateUserUseCase: GenerateUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<GenerateState>(GenerateState.Initial)

    val state = _state.asStateFlow()

    fun onGenerateClicked(gender: String, nat: String) {
        viewModelScope.launch {

            _state.value = GenerateState.Loading

            val result = generateUserUseCase(
                gender = gender.lowercase(),
                nat = nat
            )

            if (result != null) {
                _state.value = GenerateState.Success(result)
            } else {
                _state.value = GenerateState.Error("Не получилось загрузить данные. Возможно отсутствует интернет.")
            }
        }
    }
}