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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import pt.brunoponte.aptoidestore.R
import pt.brunoponte.aptoidestore.databinding.FragmentAppDetailsBinding

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

        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            setupView(viewState)
        }
    }

    private fun setupView(viewState: AppDetailsViewState) {
        when(viewState) {
            is AppDetailsViewState.Content -> {
                binding.loadingProgressIndicator.isVisible = false
                binding.appDetailsView.isVisible = true

                viewState.app.let {
                    val notApplicableText = getString(R.string.not_applicable)

                    Glide.with(this)
                        .load(it.graphicUrl)
                        .error(R.drawable.ic_no_image)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.appImageView)

                    binding.appNameText.text = it.name ?: notApplicableText

                    binding.ratingTextView.text =
                        if (it.rating == null || it.rating == 0f)
                            "--"
                        else
                            it.rating.toString()

                    binding.downloadsTextView.text = it.getDownloadsUiString() ?: notApplicableText

                    binding.sizeTextView.text = it.getSizeUiString() ?: notApplicableText

                    binding.lastUpdateTextView.text = it.getUpdatedDateUiString() ?: notApplicableText
                }
            }
            is AppDetailsViewState.Error -> {
                binding.loadingProgressIndicator.isVisible = false
                binding.appDetailsView.isVisible = false
            }
            AppDetailsViewState.Loading -> {
                binding.appDetailsView.isVisible = false
                binding.loadingProgressIndicator.isVisible = true
            }
        }


    }

}