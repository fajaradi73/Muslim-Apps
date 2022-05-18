package com.fajarproject.muslimapps.ui.alquran

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajarproject.muslimapps.databinding.ActivityAlQuranBinding
import com.fajarproject.muslimapps.models.alquran.ModelSurah
import com.fajarproject.muslimapps.util.Util

class AlQuranActivity : AppCompatActivity(), AlQuranView {

    private lateinit var alQuranBinding: ActivityAlQuranBinding
    private lateinit var presenter: AlQuranPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alQuranBinding = ActivityAlQuranBinding.inflate(layoutInflater)
        setContentView(alQuranBinding.root)
        presenter = AlQuranPresenter(this, this)
        setToolbar()
        setUI()
    }

    override fun onResume() {
        super.onResume()
        presenter.getQuran()
    }

    override fun setToolbar() {
        setSupportActionBar(alQuranBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Daftar Surah"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUI() {
        setAction()
    }

    override fun setAction() {

    }

    override fun showDataSuccess(list: MutableList<ModelSurah>) {
        alQuranBinding.rvSurah.layoutManager = LinearLayoutManager(this)
        val adapter = AlQuranAdapter(this, list)
        alQuranBinding.rvSurah.adapter = adapter
        alQuranBinding.rvSurah.setHasFixedSize(true)
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