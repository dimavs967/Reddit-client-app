package com.task.reddit.ui.screens.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.reddit.R
import com.task.reddit.databinding.FragmentHomeBinding
import com.task.reddit.model.ImageModel
import com.task.reddit.adapter.ArticlesAdapter
import com.task.reddit.adapter.DefaultLoadStateAdapter
import com.task.reddit.util.ArticleDiffCallback
import com.task.reddit.ui.screens.base.BaseFragment
import com.task.reddit.util.NetworkUtility
import com.task.reddit.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: MainViewModel by activityViewModels()
    private var adapter: ArticlesAdapter? = null

    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        getData()
        setAdapterClickListener()
        checkConnection()
    }

    private fun setAdapter() {
        adapter = ArticlesAdapter(diffCallback = ArticleDiffCallback)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter?.withLoadStateHeaderAndFooter(
            footer = DefaultLoadStateAdapter { adapter?.retry() },
            header = DefaultLoadStateAdapter { adapter?.retry() }
        )
    }

    private fun getData() {
        viewModel.getListLivaData().observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                adapter?.submitData(it)
            }
        }
    }

    private fun setAdapterClickListener() {
        adapter?.onClickListener {
            navigator.open(
                HomeFragmentDirections.actionHomeFragmentToMediaBottomSheetFragment(
                    ImageModel(it.title, it.url)
                )
            )
        }
    }

    private fun checkConnection() {
        NetworkUtility().getStateLiveData(requireContext()).observe(viewLifecycleOwner) {
            if (!it)
                Toast.makeText(requireContext(), R.string.offline_text, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

}