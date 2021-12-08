package com.amalip.teachers.presentation.student

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amalip.teachers.R
import com.amalip.teachers.core.extension.failure
import com.amalip.teachers.core.extension.observe
import com.amalip.teachers.core.presentation.BaseFragment
import com.amalip.teachers.core.presentation.BaseViewState
import com.amalip.teachers.databinding.StudentFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class StudentFragment : BaseFragment(R.layout.student_fragment) {

    private lateinit var binding: StudentFragmentBinding

    private val studentViewModel by viewModels<StudentViewModel>()
    private val args: StudentFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        studentViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)

            user.value = args.student

            getCoursesByIdAndUser(args.courseId)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is StudentViewState.UpdatedGradeForPartial -> showToast(
                getString(
                    R.string.text_updated_grade,
                    state.partial
                )
            )
        }
    }

    override fun setBinding(view: View) {
        binding = StudentFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        binding.apply {
            vm = studentViewModel

            btnUpdate.setOnClickListener { studentViewModel.setGrade(args.courseId) }

        }

        baseActivity.setBottomNavVisibility(View.GONE)
    }

}