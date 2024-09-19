package com.example.startzplayassignment.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.library.models.MediaItem
import com.example.startzplayassignment.DetailScreenActivity
import com.example.startzplayassignment.databinding.ItemMediaBinding

class MediaItemAdapter(private val mediaItems: List<MediaItem>) :
    RecyclerView.Adapter<MediaItemAdapter.MediaItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemViewHolder {
        val binding = ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) {
        val mediaItem = mediaItems[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailScreenActivity::class.java).apply {
                putExtra("media_item", mediaItem)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.bind(mediaItem)
    }

    override fun getItemCount(): Int = mediaItems.size

    inner class MediaItemViewHolder(private val binding: ItemMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mediaItem: MediaItem) {
            // Load the image into ImageView (use Glide, Picasso, etc.)
             Glide.with(binding.mediaImage.context).load("https://image.tmdb.org/t/p/w500/"+mediaItem.posterPath).into(binding.mediaImage)
        }
    }
}
