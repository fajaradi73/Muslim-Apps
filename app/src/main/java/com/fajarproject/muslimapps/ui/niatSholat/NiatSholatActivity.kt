package com.fajarproject.muslimapps.ui.niatSholat

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajarproject.muslimapps.databinding.ActivityNiatSholatBinding
import com.fajarproject.muslimapps.models.sholat.ModelNiatSholat
import com.fajarproject.muslimapps.util.Util

class NiatSholatActivity : AppCompatActivity(), NiatSholatView {
    private lateinit var binding: ActivityNiatSholatBinding
    private lateinit var presenter: NiatSholatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNiatSholatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        presenter = NiatSholatPresenter(this)
        presenter.getData()
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

    override fun showDataSuccess(list: MutableList<ModelNiatSholat>) {
        binding.rvNiatShalat.layoutManager = LinearLayoutManager(this)
        binding.rvNiatShalat.adapter = NiatSholatAdapter(list)
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