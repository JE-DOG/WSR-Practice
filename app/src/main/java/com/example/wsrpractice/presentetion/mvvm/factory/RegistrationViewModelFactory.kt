package com.example.wsrpractice.presentetion.mvvm.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wsrpractice.data.network.impl.user.UserNetworkImplementation
import com.example.wsrpractice.data.repository.UserRepositoryImplementation
import com.example.wsrpractice.data.storage.impl.user_password.UserStoragePasswordImpl
import com.example.wsrpractice.domain.use_case.user.password.SaveUserPasswordUseCase
import com.example.wsrpractice.domain.use_case.user.sign_in.SendCodeUseCase
import com.example.wsrpractice.domain.use_case.user.sign_in.SignInUseCase
import com.example.wsrpractice.presentetion.screens.sign_up.RegistrationViewModel

class RegistrationViewModelFactory(context: Context):ViewModelProvider.Factory{

    //network repository
    private val userNetworkImplementation by lazy {
        UserNetworkImplementation()
    }
    private val userRepositoryImplementation by lazy{
        UserRepositoryImplementation(userNetworkImplementation)
    }

    //server sign in
    private val signInUseCase by lazy {
        SignInUseCase(userRepositoryImplementation)
    }
    //server send code
    private val sendCodeUserUseCase by lazy {
        SendCodeUseCase(userRepositoryImplementation)
    }
    //storage implementation
    private val userStoragePasswordImpl by lazy {
        UserStoragePasswordImpl(context)
    }
    //storage set password
    private val saveUserPasswordUseCase by lazy {
        SaveUserPasswordUseCase(userStoragePasswordImpl)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModel(
            sendCodeUseCase = sendCodeUserUseCase,
            signInUseCase = signInUseCase,
            saveUserPasswordUseCase = saveUserPasswordUseCase
        ) as T
    }

}