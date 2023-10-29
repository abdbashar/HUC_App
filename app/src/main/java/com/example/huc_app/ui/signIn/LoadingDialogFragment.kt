package com.example.huc_app.ui.signIn

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.huc_app.R
import com.example.huc_app.databinding.DialogLoadingBinding
import com.example.huc_app.ui.base.BaseDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadingDialogFragment : BaseDialogFragment<DialogLoadingBinding>() {
    override val layoutIdFragment: Int = R.layout.dialog_loading
    override val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
    }
}
