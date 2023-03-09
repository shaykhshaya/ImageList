package com.shaya.fliptree.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shaya.fliptree.R
import com.shaya.fliptree.databinding.ActivityMainBinding
import com.shaya.fliptree.ui.adapter.ImageListAdapter
import com.shaya.fliptree.ui.viewmodel.ImageApiStatus
import com.shaya.fliptree.ui.viewmodel.ImageListViewModel
import com.shaya.fliptree.utils.INTENT_KEY_IMAGE_ITEM

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ImageListViewModel


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        viewModel = ViewModelProvider(this)[ImageListViewModel::class.java]
        binding.apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
            swipeRefresh.setOnRefreshListener(this@MainActivity)
        }

        setGridSpan()
        binding.recyclerViewList.adapter = ImageListAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(INTENT_KEY_IMAGE_ITEM, it)
            startActivity(intent)
        }

        binding.inputLayoutSearch.editText?.addTextChangedListener {
            viewModel.onFilter(it.toString())
        }

        viewModel.status.observe(this) {
            when (it) {
                ImageApiStatus.LOADING -> binding.swipeRefresh.isRefreshing = true
                else -> binding.swipeRefresh.isRefreshing = false
            }
        }

    }

    override fun onRefresh() {
        viewModel.getImagesList()
    }

    private fun setGridSpan() {
        val isTablet = resources.getBoolean(R.bool.is_tablet)
        binding.recyclerViewList.layoutManager = GridLayoutManager(this, if (isTablet) 7 else 3)
    }
}