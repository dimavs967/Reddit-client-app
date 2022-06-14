package com.task.reddit.ui.screens.media

import android.os.Bundle
import android.view.View
import com.task.reddit.databinding.FragmentMediaBinding
import com.task.reddit.ui.screens.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaFragment : BaseFragment<FragmentMediaBinding>() {

    override fun getViewBinding(): FragmentMediaBinding =
        FragmentMediaBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}