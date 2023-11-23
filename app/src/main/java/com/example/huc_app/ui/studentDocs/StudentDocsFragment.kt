package com.example.huc_app.ui.studentDocs

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils.loadAnimation
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentStudentDocsBinding
import com.example.huc_app.ui.base.BaseFragment
import com.example.huc_app.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentDocsFragment : BaseFragment<FragmentStudentDocsBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_student_docs

    override val viewModel: StudentDocsViewModel by viewModels()

    private lateinit var studentDocsAdapter: StudentDocsAdapter

    override var bottomNavigationViewVisibility = View.GONE


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeUIState()
        observeEvents()
    }

    private fun observeUIState() {
        lifecycleScope.launch {
            viewModel.studentDocsUIState.collect {
                it.studentDocs.let { studentDocs -> studentDocsAdapter.setItems(studentDocs.documents) }
            }
        }
    }

    private fun setAdapter() {
        studentDocsAdapter = StudentDocsAdapter(emptyList(), viewModel)
        binding.studentDocs.adapter = studentDocsAdapter
    }

    private fun observeEvents() {
        viewModel.isArrowBackClicked.observeEvent(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }

        viewModel.isOpenOptionClicked.observeEvent(viewLifecycleOwner) {
            lifecycleScope.launch {
                if (viewModel.checkInternetConnection()) {
                    if (it.documentUrl.contains(".pdf")) {
                        requireContext().openPdfWithExternalApp(it.documentUrl)
                    } else {
                        showImage(it.documentUrl)
                    }
                } else {
                    requireContext().showSnackBar(binding.root, R.string.error_no_internet)
                }
            }
        }

        viewModel.isDownloadOptionClicked.observeEvent(viewLifecycleOwner) {
            lifecycleScope.launch {
                if (viewModel.checkInternetConnection()) {
                    if ((requireActivity() as AppCompatActivity).hasWriteExternalStoragePermission()) {
                        (requireActivity() as AppCompatActivity).downloadPdfFromUrl(
                            it.documentUrl,
                            it.documentName
                        )
                    } else {
                        (requireActivity() as AppCompatActivity).requestWriteExternalStoragePermission()
                    }
                } else {
                    requireContext().showSnackBar(binding.root, R.string.error_no_internet)
                }
            }
        }

        viewModel.downloadID.observeEvent(viewLifecycleOwner) { downloadId ->
            (requireActivity() as AppCompatActivity).registerDownloadCompleteReceiver(
                downloadId,
                R.string.file_saved_to_downloads
            )
        }

        viewModel.studentDocsItemUIState.observeEvent(viewLifecycleOwner) { document ->
            lifecycleScope.launch {
                if (viewModel.checkInternetConnection()) {

                    if (document.documentUrl.contains(".pdf"))
                        requireContext().openPdfWithExternalApp(document.documentUrl)
                    else showImage(document.documentUrl)

                } else {
                    requireContext().showSnackBar(binding.guideline, R.string.error_no_internet)
                }
            }
        }
        viewModel.isContactClicked.observeEvent(viewLifecycleOwner) {
            if (it) findNavController().navigate(StudentDocsFragmentDirections.actionStudentDocsFragmentToIssueManagementFragment())
        }

    }

    private fun showImage(imageUrl: String) {
        requireContext().loadImageWithAnimation(binding.myImageView, imageUrl)
        binding.apply {
            topAppBarLinearLayout.visibility = View.INVISIBLE
            viewContainer.visibility = View.INVISIBLE
        }
    }
}

