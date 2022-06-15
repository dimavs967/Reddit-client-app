package com.task.reddit.ui.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.reddit.databinding.FragmentHomeBinding
import com.task.reddit.ui.screens.home.adapter.ArticlesAdapter
import com.task.reddit.ui.screens.home.adapter.ArticleDiffCallback
import com.task.reddit.ui.screens.base.BaseFragment
import com.task.reddit.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private var adapter: ArticlesAdapter? = null

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ArticlesAdapter(diffCallback = ArticleDiffCallback)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.getListLivaData().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter?.submitData(it)
            }
        }
    }

}