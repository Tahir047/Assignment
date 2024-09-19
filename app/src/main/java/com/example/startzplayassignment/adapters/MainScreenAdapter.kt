package com.example.startzplayassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.library.models.MediaItem
import com.example.startzplayassignment.databinding.ItemCarouselBinding

class MainScreenAdapter : RecyclerView.Adapter<MainScreenAdapter.CarouselViewHolder>() {
    private val defaultCarousels: ArrayList<List<MediaItem>> = ArrayList()
    private val searchResults: ArrayList<List<MediaItem>> = ArrayList()
    private val carousels: ArrayList<List<MediaItem>> = ArrayList()
    private val mediaTypes: ArrayList<String> = ArrayList()

    // Set the default data when search is closed
    fun setDefaultData(carousels: ArrayList<List<MediaItem>>) {
        defaultCarousels.clear()
        defaultCarousels.addAll(carousels)
        this.carousels.clear()
        this.carousels.addAll(defaultCarousels)

        // Extract media types from carousels
        mediaTypes.clear()
        mediaTypes.addAll(carousels.mapNotNull { it.firstOrNull()?.mediaType }.distinct())

        notifyDataSetChanged()
    }

    // Set data for search results
    fun setSearchResults(carousels: ArrayList<List<MediaItem>>) {
        searchResults.clear()
        searchResults.addAll(carousels)
        this.carousels.clear()
        this.carousels.addAll(searchResults)
        this.carousels.reverse()

        // Extract media types from carousels
        mediaTypes.clear()
        mediaTypes.addAll(carousels.mapNotNull { it.firstOrNull()?.mediaType }.distinct())

        notifyDataSetChanged()
    }

    // Clear data when search is opened
    fun clearData() {
        carousels.clear()
        mediaTypes.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val carousel = carousels.getOrNull(position) ?: emptyList()
        val mediaType = mediaTypes.getOrElse(position) { "Unknown" } // Default to "Unknown" if out of bounds
        holder.bind(carousel, mediaType)
    }

    override fun getItemCount(): Int = carousels.size

    inner class CarouselViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(carousel: List<MediaItem>, mediaType: String) {
            binding.carouselTitle.text = mediaType
            binding.mediaItemRecyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

            val mediaAdapter = MediaItemAdapter(carousel)
            binding.mediaItemRecyclerView.adapter = mediaAdapter
        }
    }
}
