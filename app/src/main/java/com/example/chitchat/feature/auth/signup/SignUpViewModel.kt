package com.example.chitchat.feature.auth.signup
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel(){
    private val _state = MutableStateFlow<SignUpState>(SignUpState.Nothing)
    val state = _state.asStateFlow()

    fun SignUp(name: String, email: String, password: String, confirmPassword: String){
        _state.value = SignUpState.Loading
        //Firebase SignIn
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    task.result.user?.let{
                        it.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())
                            ?.addOnCompleteListener {
                                _state.value = SignUpState.Success
                            }

                    return@addOnCompleteListener
                    }
                    _state.value = SignUpState.Error
                }else{
                    _state.value = SignUpState.Error
                }
            }

    }
}

sealed class SignUpState{
    object Nothing: SignUpState()
    object Loading: SignUpState()
    object Success: SignUpState()
    object Error: SignUpState()
}