package com.example.startzplayassignment


import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.startzplayassignment.databinding.ActivityPlaybackBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class PlaybackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaybackBinding
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaybackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaItem = intent.getParcelableExtra<com.example.library.models.MediaItem>("media_item")

        mediaItem?.let {
            // Initialize ExoPlayer
            player = ExoPlayer.Builder(this).build().apply {
                binding.playerView.player = this
            }

            // Set the media item URI
            val videoUri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
            val mediaItem = MediaItem.fromUri(videoUri)
            player?.setMediaItem(mediaItem)
            player?.prepare()
            player?.playWhenReady = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release the player when the activity is destroyed
        player?.release()
        player = null
    }
}
