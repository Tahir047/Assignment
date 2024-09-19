package com.example.startzplayassignment



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.library.domain.MainViewModel
import com.example.library.models.MediaItem
import com.example.startzplayassignment.databinding.ActivityDetailScreenBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailScreenBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaItem = intent.getParcelableExtra<MediaItem>("media_item")
        println("DetailScreenActivityMediaItem $mediaItem")
        mediaItem?.let {
            with(binding) {
                // Load the image using Glide
                Glide.with(this@DetailScreenActivity)
                    .load("https://image.tmdb.org/t/p/w500/"+it.posterPath)
                    .into(itemImageView)

                // Set item details
                itemTitleTextView.text = it.title ?: it.name
                itemDescriptionTextView.text = it.overview

                // Show or hide play button based on media type
                playButton.apply {
                    visibility = Button.VISIBLE
                    setOnClickListener {
                        // Handle playback action
                        val playbackIntent = Intent(this@DetailScreenActivity, PlaybackActivity::class.java)
                        playbackIntent.putExtra("media_item", mediaItem)
                        startActivity(playbackIntent)
                    }
                }
            }
        }
    }
}
