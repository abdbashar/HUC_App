package com.example.huc_app.ui.signIn

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentSignInBinding
import com.example.huc_app.ui.base.BaseFragment
import com.example.huc_app.util.observeEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_sign_in
    override var bottomNavigationViewVisibility = View.GONE
    override val viewModel: SignInViewModel by viewModels()

    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.getGoogleSignInCredentials(result.data)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }
    private fun observeEvents() {
        viewModel.isSignInButtonClicked.observeEvent(viewLifecycleOwner) { condition ->
            if (condition) {
                handleSignInButtonClicked()
            }
        }
        viewModel.signInEvent.observeEvent(viewLifecycleOwner) {
            if (it){
                requireActivity().recreate()
            }
        }
        lifecycleScope.launch {
            viewModel.signInUiState.collectLatest {
                when {
                    it.isLoading  -> showLoadingDialog()
                    it.isSuccess -> {
                        dismissLoadingDialog()
                        viewModel.signInUiState.value.isSuccess = false
                    }
                    it.isError -> {
                        dismissLoadingDialog()
                        showSnackBar(it.error)
                        viewModel.signInUiState.value.isError = false
                    }
                }
            }
        }
    }
    private fun handleSignInButtonClicked() {
        lifecycleScope.launch {
            if (viewModel.checkInternetConnection()) {
                if (viewModel.isCoolDownPeriodActive()) {
                    showSnackBar(getString(R.string.error_cool_down, viewModel.getRemainingTime()))
                } else {
                    initiateGoogleSignIn()
                }
            } else {
                showSnackBar(getString(R.string.error_no_internet))
            }
        }
    }
    private fun initiateGoogleSignIn() {
        lifecycleScope.launch {
            val signInResult = viewModel.beginGoogleOneTapSignIn()
            signInResult.let { result ->
                signInLauncher.launch(
                    IntentSenderRequest.Builder(result.pendingIntent.intentSender)
                        .build()
                )
            }
        }
    }

    private fun showLoadingDialog() {
        childFragmentManager.beginTransaction()
            .add(LoadingDialogFragment(), "loading_dialog")
            .commitAllowingStateLoss()
    }
    private fun dismissLoadingDialog() {
        val loadingDialog = childFragmentManager.findFragmentByTag("loading_dialog")
        if (loadingDialog is LoadingDialogFragment) {
            loadingDialog.dismissAllowingStateLoss()
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}