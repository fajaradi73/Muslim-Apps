package com.fajarproject.muslimapps.ui.jadwalsholat

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fajarproject.muslimapps.databinding.ActivityJadwalSholatBinding
import com.fajarproject.muslimapps.models.sholat.DaftarKota
import com.fajarproject.muslimapps.models.sholat.ModelSholat
import com.fajarproject.muslimapps.util.Util

class JadwalSholatActivity : AppCompatActivity(), JadwalSholatView {
    private lateinit var binding: ActivityJadwalSholatBinding
    private lateinit var presenter: JadwalSholatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalSholatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = JadwalSholatPresenter(this)
        setToolbar()
        setUI()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Jadwal Sholat"
    }

    override fun setUI() {
        presenter.getKota()
    }

    override fun showLoading() {
        Util.showLoading(this)
    }

    override fun hideLoading() {
        Util.hideLoading()
    }

    override fun showDataKotaSuccess(list: MutableList<DaftarKota>) {
        val daftarKotaAdapter = KotaAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            list as ArrayList<DaftarKota>
        )
        daftarKotaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinKota.adapter = daftarKotaAdapter
        binding.spinKota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>) {}
            override fun onItemSelected(p0: AdapterView<*>, view: View?, position: Int, id: Long) {
                val spinKota = daftarKotaAdapter.getItem(position)
                spinKota?.id?.let { presenter.getJadwalSholat(it) }
            }
        }
    }

    override fun showDataSholatSuccess(data: ModelSholat) {
        binding.tvImsak.text = data.data?.jadwal?.imsak
        binding.tvSubuh.text = data.data?.jadwal?.subuh
        binding.tvTerbit.text = data.data?.jadwal?.terbit
        binding.tvDzuhur.text = data.data?.jadwal?.dzuhur
        binding.tvDhuha.text = data.data?.jadwal?.dhuha
        binding.tvAshar.text = data.data?.jadwal?.ashar
        binding.tvMaghrib.text = data.data?.jadwal?.maghrib
        binding.tvIsya.text = data.data?.jadwal?.isya
        binding.tvTanggal.text =
            data.data?.jadwal?.date?.let { Util.convertDate(it, "yyyy-MM-dd", "dd MMMM yyyy") }
    }

    override fun showDataFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}