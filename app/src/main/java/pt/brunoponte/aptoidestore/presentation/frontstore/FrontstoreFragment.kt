package pt.brunoponte.aptoidestore.presentation.frontstore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pt.brunoponte.aptoidestore.databinding.FragmentFrontstoreBinding
import pt.brunoponte.aptoidestore.presentation.UIHelper
import pt.brunoponte.aptoidestore.presentation.frontstore.editorsAppListAdapter.EditorsAppListAdapter
import pt.brunoponte.aptoidestore.presentation.frontstore.editorsAppListAdapter.EditorsAppListInteraction
import pt.brunoponte.aptoidestore.presentation.frontstore.topAppListAdapter.TopAppListInteraction
import pt.brunoponte.aptoidestore.presentation.frontstore.topAppListAdapter.TopAppListAdapter

@AndroidEntryPoint
class FrontstoreFragment : Fragment(), TopAppListInteraction, EditorsAppListInteraction {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val viewModel: FrontstoreViewModel by viewModels()

    private lateinit var binding: FragmentFrontstoreBinding

    private lateinit var topAppListAdapter: TopAppListAdapter
    private lateinit var editorsAppListAdapter: EditorsAppListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getApps()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFrontstoreBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topAppListAdapter = TopAppListAdapter(this).apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy
                .PREVENT_WHEN_EMPTY
        }

        binding.topRecyclerView.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }

            recyclerView.adapter = topAppListAdapter
        }

        editorsAppListAdapter = EditorsAppListAdapter(this).apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy
                .PREVENT_WHEN_EMPTY
        }

        binding.editorsRecyclerView.let { recyclerView ->
            recyclerView.layoutManager = LinearLayoutManager(context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }

            recyclerView.adapter = editorsAppListAdapter
        }

        lifecycleScope.launchWhenResumed {
            setupViewModelObservers()
        }
    }

    override fun onEditorsAppClick(appId: Long) {
        val action = FrontstoreFragmentDirections.actionFrontstoreFragmentToAppDetailsFragment(appId)
        findNavController().navigate(action)
    }

    override fun onTopAppClick(appId: Long) {
        val action = FrontstoreFragmentDirections.actionFrontstoreFragmentToAppDetailsFragment(appId)
        findNavController().navigate(action)
    }

    private suspend fun setupViewModelObservers() {
        // Parse view state to show appropriate content
        viewModel.viewState.collect { viewState ->

            // If has message to show, show Snackbar
            viewState.message?.let { message ->
                UIHelper.showSnackbar(
                    binding.root,
                    message,
                    Snackbar.LENGTH_SHORT
                ) { viewModel.onMessageClosed() }
            }

            // If is loading, show loading indicator
            binding.loadingProgressBar.isVisible = viewState.isLoading


            // If has apps, show them
            if (viewState.apps == null) {
                binding.contentView.isVisible = false
            } else  {
                binding.contentView.isVisible = true
                topAppListAdapter.submitList(viewState.apps)
                editorsAppListAdapter.submitList(viewState.apps)
            }
        }
    }

}