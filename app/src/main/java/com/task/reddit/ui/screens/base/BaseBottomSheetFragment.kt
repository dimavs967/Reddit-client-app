package com.task.reddit.ui.screens.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.task.reddit.R
import com.task.reddit.navigation.Navigator
import javax.inject.Inject

abstract class BaseBottomSheetFragment<VB : ViewBinding> : BottomSheetDialogFragment() {

    @Inject
    lateinit var navigator: Navigator

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onResume() {
        super.onResume()
        navigator.setNavController(findNavController())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }

    abstract fun getViewBinding(): VB

    open fun tryGoBack() {
        navigator.goBack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}