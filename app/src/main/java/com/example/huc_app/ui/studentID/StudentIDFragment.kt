package com.example.huc_app.ui.studentID

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentStudentIdBinding
import com.example.huc_app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentIDFragment : BaseFragment<FragmentStudentIdBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_student_id

    override val viewModel: StudentIDViewModel by viewModels()

    override var bottomNavigationViewVisibility = View.GONE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}