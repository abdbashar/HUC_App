package com.example.huc_app.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentMenuBindingImpl
import com.example.huc_app.ui.base.BaseFragment
import com.example.huc_app.ui.menu.menuEvent.MenuEvent
import com.example.huc_app.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBindingImpl>() {
    override fun getLayoutId(): Int = R.layout.fragment_menu

    override val viewModel: MenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.menuEvent.observeEvent(viewLifecycleOwner){ event ->
            when (event) {
                MenuEvent.PersonalInfoClicked -> navigateToScreenById(R.id.action_menuFragment_to_personalInfoFragment)
                MenuEvent.SecondarySchoolInfoClicked -> navigateToScreenById(R.id.action_menuFragment_to_secondarySchoolInfoFragment)
                MenuEvent.UniversityInfoClicked -> navigateToScreenById(R.id.action_menuFragment_to_universityInfoFragment)
                MenuEvent.StudentDocsClicked -> navigateToScreenById(R.id.action_menuFragment_to_studentDocsFragment)
            }
        }
    }


    private fun navigateToScreenById(screenId: Int) {
        findNavController().navigate(screenId)
    }

}