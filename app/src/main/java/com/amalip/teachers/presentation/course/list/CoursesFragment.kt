package com.amalip.teachers.presentation.course.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.amalip.teachers.R
import com.amalip.teachers.core.extension.failure
import com.amalip.teachers.core.extension.observe
import com.amalip.teachers.core.presentation.BaseFragment
import com.amalip.teachers.core.presentation.BaseViewState
import com.amalip.teachers.databinding.CoursesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class CoursesFragment : BaseFragment(R.layout.courses_fragment) {

    private lateinit var binding: CoursesFragmentBinding

    private val coursesViewModel by viewModels<CoursesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coursesViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {

        }
    }

    override fun setBinding(view: View) {

        baseActivity.setBottomNavVisibility(View.VISIBLE)
    }

}