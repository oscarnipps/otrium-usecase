package com.android.otrium.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.otrium.domain.entity.UserDetails
import com.android.otrium.ui.databinding.FragmentGithubProfileBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GithubProfileFragment : Fragment() {

    private val viewModel: GithubProfileViewModel by viewModels()
    private lateinit var binding: FragmentGithubProfileBinding
    private val pinnedRepoAdapter = PinnedRepoAdapter()
    private var starredRepoAdapter = StarredRepoAdapter()
    private val topRepoAdapter = TopRepoAdapter()
    private lateinit var pinnedRepoRecyclerView: RecyclerView
    private lateinit var topRepoRecyclerView: RecyclerView
    private lateinit var starredRepoRecyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_github_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()

        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.combinedResult.collect {

                    swipeRefreshLayout.isRefreshing = false

                    when (it) {
                        is GithubProfileScreenState.Loading -> {
                            //show progress bar or loading screen
                        }

                        is GithubProfileScreenState.Error -> {
                            //handle error or show message
                            it.appError
                        }

                        is GithubProfileScreenState.Success -> {

                            it.userDetails?.let {
                                updateViews(it)
                            }
                        }

                        else -> {
                            //some custom logic which is not required in this scenario
                        }
                    }
                }
            }
        }
    }

    private fun updateViews(userDetails: UserDetails) {
        binding.profileEmail.text = userDetails.user?.email

        binding.profileName.text = userDetails.user?.name

        binding.profileLogin.text = userDetails.user?.login

        binding.profileEmail.text = userDetails.user?.email

        binding.profileFollowers.text =
            getString(R.string.followers, userDetails.user?.followers.toString())

        binding.profileFollowing.text =
            getString(R.string.following, userDetails.user?.following.toString())

        Glide.with(this)
            .load(userDetails.user?.profileUrl)
            .into(binding.userImage)

        pinnedRepoAdapter.submitList(userDetails.pinnedRepos)

        topRepoAdapter.submitList(userDetails.topRepos)

        starredRepoAdapter.submitList(userDetails.starredRepos)


    }

    private fun initializeViews() {
        pinnedRepoRecyclerView = binding.pinnedRecyclerview
        pinnedRepoRecyclerView.adapter = pinnedRepoAdapter

        topRepoRecyclerView = binding.topRecyclerview
        topRepoRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        topRepoRecyclerView.adapter = topRepoAdapter

        starredRepoRecyclerView = binding.starredRecyclerview
        starredRepoRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        starredRepoRecyclerView.adapter = starredRepoAdapter

        swipeRefreshLayout = binding.swipeToRefresh

        swipeRefreshLayout.setOnRefreshListener {
            // Trigger data refresh
            viewModel.getUserFromApi(isForceRefresh = true)
        }
    }

}