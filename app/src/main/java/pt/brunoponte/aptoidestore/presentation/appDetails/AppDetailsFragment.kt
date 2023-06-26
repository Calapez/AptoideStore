package pt.brunoponte.aptoidestore.presentation.appDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import pt.brunoponte.aptoidestore.R
import pt.brunoponte.aptoidestore.databinding.FragmentAppDetailsBinding
import javax.inject.Inject

@AndroidEntryPoint
class AppDetailsFragment : Fragment(), AppDetailsContract.View {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    @Inject
    lateinit var presenter: AppDetailsContract.Presenter

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

        presenter.setView(this)

        val appId = args.appId
        presenter.setAppId(appId)
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }

    override fun showLoadingState() {
        binding.contentView.isVisible = false
        binding.errorView.isVisible = false
        binding.loadingProgressIndicator.isVisible = true
    }

    override fun showContentState(app: AppDetailsUiModel) {
        binding.loadingProgressIndicator.isVisible = false
        binding.errorView.isVisible = false
        binding.contentView.isVisible = true

        app.run {
            val notApplicableText = getString(R.string.not_applicable)

            Glide.with(this@AppDetailsFragment)
                .load(this.graphicUrl)
                .error(R.drawable.ic_no_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.appImageView)

            binding.appNameText.text = this.name ?: notApplicableText

            binding.ratingTextView.text =
                if (this.rating == null || this.rating == 0f)
                    "--"
                else
                    this.rating.toString()

            binding.downloadsTextView.text = this.getDownloadsUiString() ?: notApplicableText

            binding.sizeTextView.text = this.getSizeUiString() ?: notApplicableText

            binding.lastUpdateTextView.text = this.getUpdatedDateUiString() ?: notApplicableText
        }
    }

    override fun showErrorState(errorMsg: String) {
        binding.loadingProgressIndicator.isVisible = false
        binding.contentView.isVisible = false
        binding.errorView.isVisible = true
        binding.errorText.text = errorMsg
    }

}
