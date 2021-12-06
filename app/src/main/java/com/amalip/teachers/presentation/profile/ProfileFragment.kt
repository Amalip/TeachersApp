package com.amalip.teachers.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.amalip.teachers.R
import com.amalip.teachers.core.extension.failure
import com.amalip.teachers.core.extension.observe
import com.amalip.teachers.core.presentation.BaseFragment
import com.amalip.teachers.core.presentation.BaseViewState
import com.amalip.teachers.databinding.ProfileFragmentBinding

class ProfileFragment : BaseFragment(R.layout.profile_fragment) {

    private lateinit var binding: ProfileFragmentBinding

    private val profileViewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileViewModel.apply {
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