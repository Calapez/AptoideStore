package pt.brunoponte.aptoidestore.presentation.appDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import pt.brunoponte.aptoidestore.R
import pt.brunoponte.aptoidestore.databinding.FragmentAppDetailsBinding
import pt.brunoponte.aptoidestore.presentation.UIHelper
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

    override fun setLoading(enabled: Boolean) {
        activity?.runOnUiThread {
            binding.loadingProgressBar.isVisible = enabled
        }
    }

    override fun setApp(app: AppDetailsUiModel?) {
        activity?.runOnUiThread {
            if (app == null) {
                binding.contentView.isVisible = false
                return@runOnUiThread
            }

            binding.contentView.isVisible = true

            val notApplicableText = getString(R.string.not_applicable)

            Glide.with(this@AppDetailsFragment)
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

    override fun setMessage(message: String) {
        activity?.runOnUiThread {
            UIHelper.showSnackbar(
                binding.root,
                message,
                Snackbar.LENGTH_SHORT
            ) { presenter.onMessageClosed() }
        }
    }

}