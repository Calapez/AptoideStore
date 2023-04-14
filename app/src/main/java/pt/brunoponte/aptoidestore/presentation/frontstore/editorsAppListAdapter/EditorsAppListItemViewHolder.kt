package pt.brunoponte.aptoidestore.presentation.frontstore.editorsAppListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pt.brunoponte.aptoidestore.R
import pt.brunoponte.aptoidestore.databinding.EditorsAppListItemBinding
import pt.brunoponte.aptoidestore.presentation.frontstore.AppUiItem

class EditorsAppListItemViewHolder(
    private val binding: EditorsAppListItemBinding,
    private val interaction: EditorsAppListInteraction
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(app: AppUiItem) {
        val notApplicableText = itemView.context.getString(R.string.not_applicable)

        if (app.graphicUrl == null) {
            binding.appImageView.setImageResource(R.drawable.ic_no_image)
        } else {
            Glide.with(itemView.context)
                .load(app.graphicUrl)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_no_image)
                .centerCrop()
                .into(binding.appImageView)
        }

        binding.appNameText.text = app.name ?: notApplicableText
        binding.ratingLabelTextView.text = app.rating?.toString() ?: notApplicableText

        binding.appCard.setOnClickListener {
            interaction.onEditorsAppClick(app.id)
        }
    }
}