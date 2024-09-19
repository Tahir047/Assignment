package com.example.startzplayassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.startzplayassignment.databinding.ItemCarouselBinding

//class CarouselAdapter(private val carousels: List<Carousel>) :
//    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
//        val binding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return CarouselViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
//        val carousel = carousels[position]
//        holder.bind(carousel)
//    }
//
//    override fun getItemCount(): Int = carousels.size
//
//    inner class CarouselViewHolder(private val binding: ItemCarouselBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(carousel: Carousel) {
//            binding.carouselTitle.text = carousel.name
//
//            // Set up the horizontal media item adapter for each carousel
//            val mediaItemAdapter = MediaItemAdapter(carousel.mediaItems)
//            binding.mediaItemRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
//            binding.mediaItemRecyclerView.adapter = mediaItemAdapter
//        }
//    }
//}
