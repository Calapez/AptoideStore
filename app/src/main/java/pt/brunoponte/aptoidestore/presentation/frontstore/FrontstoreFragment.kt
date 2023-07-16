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
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsContract
import pt.brunoponte.aptoidestore.presentation.frontstore.editorsAppListAdapter.EditorsAppListAdapter
import pt.brunoponte.aptoidestore.presentation.frontstore.editorsAppListAdapter.EditorsAppListInteraction
import pt.brunoponte.aptoidestore.presentation.frontstore.topAppListAdapter.TopAppListInteraction
import pt.brunoponte.aptoidestore.presentation.frontstore.topAppListAdapter.TopAppListAdapter
import javax.inject.Inject

@AndroidEntryPoint
class FrontstoreFragment : Fragment(), FrontstoreContract.View, TopAppListInteraction, EditorsAppListInteraction {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    @Inject
    lateinit var presenter: FrontstoreContract.Presenter

    private lateinit var binding: FragmentFrontstoreBinding

    private lateinit var topAppListAdapter: TopAppListAdapter
    private lateinit var editorsAppListAdapter: EditorsAppListAdapter

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

        presenter.setView(this)

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
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }

    override fun setLoading(enabled: Boolean) {
        activity?.runOnUiThread {
            binding.loadingProgressBar.isVisible = enabled
        }
    }

    override fun setApps(apps: List<AppItemUiModel>?) {
        activity?.runOnUiThread {
            if (apps == null) {
                binding.contentView.isVisible = false
            } else {
                binding.contentView.isVisible = true
                topAppListAdapter.submitList(apps)
                editorsAppListAdapter.submitList(apps)
            }
        }
    }

    override fun setMessage(message: String) {
        activity?.runOnUiThread {
            UIHelper.showSnackbar(
                binding.root,
                message,
                Snackbar.LENGTH_SHORT
            ) { presenter.onMessageClosed() }
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

}