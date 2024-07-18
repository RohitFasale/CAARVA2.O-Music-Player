package com.example.caarvamusicplayer2o

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.caarvamusicplayer2o.adapter.SongsListAdapter
import com.example.caarvamusicplayer2o.databinding.ActivitySongsListBinding
import com.example.caarvamusicplayer2o.models.CategoryModel

class SongsListActivity : AppCompatActivity() {

    companion object {
        lateinit var category: CategoryModel
    }

    private lateinit var binding: ActivitySongsListBinding
    private lateinit var songsListAdapter: SongsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameTextView.text = category.name

        Glide.with(this)
            .load(category.coverUrl)
            .apply(RequestOptions().transform(RoundedCorners(32)))
            .into(binding.coverImageView)

        setupSongsListRecyclerView()
    }

    private fun setupSongsListRecyclerView() {
        songsListAdapter = SongsListAdapter(category.songs)
        binding.songsListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.songsListRecyclerView.adapter = songsListAdapter
    }
}
