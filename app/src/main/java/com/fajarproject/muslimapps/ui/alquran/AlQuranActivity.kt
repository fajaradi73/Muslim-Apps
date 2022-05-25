package com.fajarproject.muslimapps.ui.alquran

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.fajarproject.muslimapps.R
import com.fajarproject.muslimapps.databinding.ActivityAlQuranBinding
import com.fajarproject.muslimapps.models.alquran.ModelSurah
import com.fajarproject.muslimapps.util.Util


class AlQuranActivity : AppCompatActivity(), AlQuranView {

    private lateinit var binding: ActivityAlQuranBinding
    private lateinit var presenter: AlQuranPresenter
    private lateinit var activity: Activity
    private lateinit var adapter: AlQuranAdapter
    private var search = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlQuranBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = AlQuranPresenter(this)
        activity = this
        setToolbar()
        setUI()
    }

    override fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Daftar Surah"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        } else if (item.itemId == R.id.action_search) {
            openSearch()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun setUI() {
        presenter.getQuran()
    }

    override fun openSearch() {
        binding.search.visibility = View.VISIBLE
        binding.search.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {

            }

            override fun onSearchAction(currentQuery: String?) {
                closeSearch()
                search = currentQuery.toString()
                adapter.filter.filter(search)
            }
        })
        binding.search.setSearchFocused(true)
        binding.search.setOnHomeActionClickListener {
            closeSearch()
        }
    }

    override fun onBackPressed() {
        if (!binding.search.setSearchFocused(false) || binding.search.visibility == View.VISIBLE) {
            closeSearch()
        }
        super.onBackPressed()
    }

    override fun closeSearch() {
        binding.search.visibility = View.GONE
        adapter.filter.filter(search)
    }

    override fun showDataSuccess(list: MutableList<ModelSurah>) {
        binding.rvSurah.layoutManager = LinearLayoutManager(this)
        adapter = AlQuranAdapter(this, list)
        binding.rvSurah.adapter = adapter
        binding.rvSurah.setHasFixedSize(true)
    }

    override fun showDataFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        Util.showLoading(this)
    }

    override fun hideLoading() {
        Util.hideLoading()
    }

}