package pt.brunoponte.aptoidestore.presentation.frontstore.editorsAppListAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pt.brunoponte.aptoidestore.databinding.EditorsAppListItemBinding
import pt.brunoponte.aptoidestore.presentation.frontstore.AppItemUiModel

interface EditorsAppListInteraction {
    fun onEditorsAppClick(appId: Long)
}

class EditorsAppListAdapter(
    private val interaction: EditorsAppListInteraction
) : ListAdapter<AppItemUiModel, EditorsAppListItemViewHolder>(EditorsAppListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditorsAppListItemViewHolder {
        val itemBinding = EditorsAppListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EditorsAppListItemViewHolder(itemBinding, interaction)
    }

    override fun onBindViewHolder(holder: EditorsAppListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object : DiffUtil.ItemCallback<AppItemUiModel>() {

        override fun areItemsTheSame(oldItem: AppItemUiModel, newItem: AppItemUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AppItemUiModel, newItem: AppItemUiModel): Boolean {
            return oldItem == newItem
        }
    }
}