package com.example.huc_app.ui.signIn

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentSignInBinding
import com.example.huc_app.ui.base.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_sign_in
    override val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}