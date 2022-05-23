package com.fajarproject.muslimapps.ui.doa

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajarproject.muslimapps.databinding.ActivityDoaBinding
import com.fajarproject.muslimapps.models.doa.ModelDoa
import com.fajarproject.muslimapps.util.Util

class DoaActivity : AppCompatActivity(), DoaView {
    private lateinit var binding: ActivityDoaBinding
    private lateinit var presenter: DoaPresenter
    private var search = ""
    private lateinit var adapter: DoaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = DoaPresenter(this)
        setToolbar()
        setUI()
    }

    override fun setUI() {
        presenter.getData()
        binding.searchDoa.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search = query
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                search = newText
                adapter.filter.filter(newText)
                return false
            }
        })
        binding.searchDoa.setOnSearchClickListener {
            adapter.filter.filter(search)
        }
    }

    override fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        Util.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.TRANSPARENT
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showDataSuccess(list: MutableList<ModelDoa>) {
        binding.rvListDoa.layoutManager = LinearLayoutManager(this)
        adapter = DoaAdapter(list)
        binding.rvListDoa.adapter = adapter
        binding.rvListDoa.setHasFixedSize(true)
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