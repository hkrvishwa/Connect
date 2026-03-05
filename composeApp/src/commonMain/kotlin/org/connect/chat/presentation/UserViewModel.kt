package org.connect.chat.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.connect.chat.data.User
import org.connect.chat.data.UserRepository

class UserViewModel(val userRepository: UserRepository)  : ViewModel() {
    private val _user  = MutableStateFlow<UserData>(UserData())
    val user : StateFlow<UserData> = _user.asStateFlow()
    var job : Job? = null

    fun getUsers(){
        job?.cancel()
        job = viewModelScope.launch {
            _user.update {
                it.copy(isLoading = true)
            }

            val users = userRepository.getUsers()
            if(users==null || users.isEmpty()){
                _user.update {
                    it.copy(isLoading = false,
                        error = "no data found"
                    )
                }
                return@launch
            }
            _user.update {
                it.copy(
                    isLoading = false,
                    userList = users
                )
            }
        }
    }

}

data class UserData(
    val isLoading : Boolean? = null,
    val userList : List<User>? = null,
    val error : String? = null
)