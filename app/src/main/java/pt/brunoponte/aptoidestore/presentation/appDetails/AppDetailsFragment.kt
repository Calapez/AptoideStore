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
import pt.brunoponte.aptoidestore.R
import pt.brunoponte.aptoidestore.databinding.FragmentAppDetailsBinding

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
                binding.breedDetailsView.isVisible = true

                viewState.app?.let {
                    val notApplicableText = getString(R.string.not_applicable)

                    Glide.with(this)
                        .load(it.graphicUrl)
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_no_image)
                        .into(binding.appImageView)

                    binding.appNameText.text = it.name ?: notApplicableText

                    binding.ratingTextView.text = it.rating?.toString() ?: notApplicableText

                    binding.downloadsTextView.text = it.downloads?.toString() ?: notApplicableText

                    binding.sizeTextView.text = it.size?.toString() ?: notApplicableText

                    binding.lastUpdateTextView.text = it.updated?.toString() ?: notApplicableText
                }
            }
            is AppDetailsViewState.Error -> {
                binding.loadingProgressIndicator.isVisible = false
                binding.breedDetailsView.isVisible = false
            }
            AppDetailsViewState.Loading -> {
                binding.breedDetailsView.isVisible = false
                binding.loadingProgressIndicator.isVisible = true
            }
        }


    }

}