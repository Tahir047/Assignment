package com.example.startzplayassignment

import android.content.Context
import org.koin.android.viewmodel.ext.android.viewModel

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.library.domain.MainViewModel
import com.example.library.models.MediaItem
import com.example.startzplayassignment.adapters.MainScreenAdapter
import com.example.startzplayassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by viewModel()
    private var mainAdapter: MainScreenAdapter? = null
    private val listDefault = ArrayList<List<MediaItem>>()
    private val listSearch = ArrayList<List<MediaItem>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            mainAdapter = MainScreenAdapter()
            carouselRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            carouselRecyclerView.adapter = mainAdapter

            searchEditText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    listSearch.clear()
                    mainAdapter?.clearData()
                } else {
                    mainAdapter?.setDefaultData(listDefault)
                }
            }

            searchEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val query = s?.toString()
                    if (!query.isNullOrEmpty()) {
                        searchMovies(query)
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        viewModel.mediaItems.observe(this) { carousels ->
            listDefault.add(carousels)
            mainAdapter?.setDefaultData(listDefault)
        }

        viewModel.mediaItemsSearch.observe(this) { carousels ->
            listSearch.add(carousels)
            mainAdapter?.setSearchResults(listSearch)
        }

        viewModel.discoverMovies("movie")
        viewModel.discoverMovies("tv")
        viewModel.discoverMovies("news")
    }

    private fun searchMovies(query: String) {
        viewModel.fetchMediaItems(query)
    }

    override fun onBackPressed() {
        if (binding?.searchEditText?.hasFocus() == true) {
            binding?.apply {
                searchEditText.clearFocus()
                searchEditText.setText("")
                hideKeyboard(searchEditText)
                mainAdapter?.setDefaultData(listDefault)
            }
        } else {
            super.onBackPressed()
        }
    }

    private fun hideKeyboard(view: View?) {
        view?.let {
            val imm = it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }



}
