package com.shaya.fliptree.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shaya.fliptree.R
import com.shaya.fliptree.databinding.ActivityDetailBinding
import com.shaya.fliptree.response.ImageItem
import com.shaya.fliptree.utils.INTENT_KEY_IMAGE_ITEM
import com.shaya.fliptree.utils.loadImageByUrl

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        val imageItem = intent?.getParcelableExtra(INTENT_KEY_IMAGE_ITEM) as? ImageItem
        imageItem?.let {
            binding.ivImageDetail.loadImageByUrl(this, it.image.orEmpty())
        }
        binding.imageBack.setOnClickListener {
            finish()
        }
    }
}