package pt.brunoponte.aptoidestore.presentation.frontstore.editorsAppListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pt.brunoponte.aptoidestore.R
import pt.brunoponte.aptoidestore.databinding.EditorsAppListItemBinding
import pt.brunoponte.aptoidestore.presentation.frontstore.AppItemUiModel

class EditorsAppListItemViewHolder(
    private val binding: EditorsAppListItemBinding,
    private val interaction: EditorsAppListInteraction
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(app: AppItemUiModel) {
        val notApplicableText = itemView.context.getString(R.string.not_applicable)

        if (app.graphicUrl == null) {
            binding.appImageView.setImageResource(R.drawable.ic_no_image)
        } else {
            Glide.with(itemView.context)
                .load(app.graphicUrl)
                .error(R.drawable.ic_no_image)
                .centerCrop()
                .into(binding.appImageView)
        }

        binding.appNameText.text = app.name ?: notApplicableText
        binding.ratingLabelTextView.text =
            if (app.rating == null || app.rating == 0f)
                "--"
            else
                app.rating.toString()

        binding.appCard.setOnClickListener {
            interaction.onEditorsAppClick(app.id)
        }
    }
}