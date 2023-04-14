package pt.brunoponte.aptoidestore.presentation.frontstore.topAppListAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pt.brunoponte.aptoidestore.databinding.TopAppListItemBinding
import pt.brunoponte.aptoidestore.presentation.frontstore.AppUiItem

interface TopAppListInteraction {
    fun onTopAppClick(appId: Long)
}

class TopAppListAdapter(
    private val interaction: TopAppListInteraction
) : ListAdapter<AppUiItem, TopAppListItemViewHolder>(TopAppListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAppListItemViewHolder {
        val itemBinding= TopAppListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopAppListItemViewHolder(itemBinding, interaction)
    }

    override fun onBindViewHolder(holder: TopAppListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object : DiffUtil.ItemCallback<AppUiItem>() {

        override fun areItemsTheSame(oldItem: AppUiItem, newItem: AppUiItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AppUiItem, newItem: AppUiItem): Boolean {
            return oldItem == newItem
        }
    }
}