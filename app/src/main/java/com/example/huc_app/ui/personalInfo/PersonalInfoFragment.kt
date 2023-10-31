package com.example.huc_app.ui.personalInfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentPersonalInfoBinding
import com.example.huc_app.ui.base.BaseFragment
import com.example.huc_app.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalInfoFragment : BaseFragment<FragmentPersonalInfoBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_personal_info

    override val viewModel: PersonalInfoViewModel by viewModels()
    override var bottomNavigationViewVisibility = View.GONE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }
    private fun observeEvents() {
        viewModel.isArrowBackClicked.observeEvent(viewLifecycleOwner) {
            if (it) {
                 findNavController().popBackStack()
            }
        }
    }
}