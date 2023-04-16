package pt.brunoponte.aptoidestore.presentation.frontstore.topAppListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import pt.brunoponte.aptoidestore.R
import pt.brunoponte.aptoidestore.databinding.TopAppListItemBinding
import pt.brunoponte.aptoidestore.presentation.frontstore.AppItemUiModel

class TopAppListItemViewHolder(
    private val binding: TopAppListItemBinding,
    private val interaction: TopAppListInteraction
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(app: AppItemUiModel) {
        val notApplicableText = itemView.context.getString(R.string.not_applicable)

        if (app.iconUrl == null) {
            binding.appImageView.setImageResource(R.drawable.ic_no_image)
        } else {
            val cornerRadius = itemView.context.resources.getDimensionPixelSize(R.dimen.top_app_image_radius)

            Glide.with(itemView.context)
                .load(app.iconUrl)
                .transform(RoundedCorners(cornerRadius))
                .error(R.drawable.ic_no_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.appImageView)
        }

        binding.appNameText.text = app.name ?: notApplicableText
        binding.ratingLabelTextView.text =
            if (app.rating == null || app.rating == 0f)
                "--"
            else
                app.rating.toString()

        binding.appCard.setOnClickListener {
            interaction.onTopAppClick(app.id)
        }
    }
}