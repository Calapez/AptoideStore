package pt.brunoponte.aptoidestore.presentation.appDetails

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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pt.brunoponte.aptoidestore.R
import pt.brunoponte.aptoidestore.databinding.FragmentAppDetailsBinding
import pt.brunoponte.aptoidestore.domain.models.App
import pt.brunoponte.aptoidestore.presentation.UIHelper

@AndroidEntryPoint
class AppDetailsFragment : Fragment() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val viewModel: AppDetailsViewModel by viewModels()

    private lateinit var binding: FragmentAppDetailsBinding
    private val args: AppDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppDetailsBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appId = args.appId
        viewModel.getAppFromId(appId)

        lifecycleScope.launchWhenResumed {
            setupViewModelObservers()
        }
    }

    private suspend fun setupViewModelObservers() {
        // Parse view state to show appropriate content
        viewModel.viewState.collect { viewState ->
            // If has message to show, show Snackbar
            viewState.message?.let { message ->
                UIHelper.showSnackbar(
                    binding.root,
                    message,
                    Snackbar.LENGTH_SHORT,
                    { viewModel.onMessageClosed() }
                )
            }

            // If is loading, show loading indicator
            binding.loadingProgressBar.isVisible = viewState.isLoading


            // If has app, show it
            if (viewState.app == null) {
                binding.contentView.isVisible = false
            } else  {
                binding.contentView.isVisible = true
                fillAppUi(viewState.app)
            }
        }
    }

    private fun fillAppUi(app: AppDetailsUiModel) {
        val notApplicableText = getString(R.string.not_applicable)

        Glide.with(this)
            .load(app.graphicUrl)
            .error(R.drawable.ic_no_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.appImageView)

        binding.appNameText.text = app.name ?: notApplicableText

        binding.ratingTextView.text =
            if (app.rating == null || app.rating == 0f)
                "--"
            else
                app.rating.toString()

        binding.downloadsTextView.text = app.getDownloadsUiString() ?: notApplicableText

        binding.sizeTextView.text = app.getSizeUiString() ?: notApplicableText

        binding.lastUpdateTextView.text = app.getUpdatedDateUiString() ?: notApplicableText
    }

}