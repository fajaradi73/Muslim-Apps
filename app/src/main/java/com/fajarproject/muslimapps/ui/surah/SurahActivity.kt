package com.fajarproject.muslimapps.ui.surah

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajarproject.muslimapps.databinding.ActivitySurahBinding
import com.fajarproject.muslimapps.models.alquran.ModelAyat
import com.fajarproject.muslimapps.models.alquran.ModelSurah
import com.fajarproject.muslimapps.util.Constant
import com.fajarproject.muslimapps.util.Util
import org.parceler.Parcels
import java.io.IOException


class SurahActivity : AppCompatActivity(), SurahView {
    private lateinit var binding: ActivitySurahBinding
    private lateinit var presenter: SurahPresenter
    private lateinit var modelSurah: ModelSurah
    private lateinit var strNomor: String
    private lateinit var strNama: String
    private lateinit var strArti: String
    private lateinit var strType: String
    private lateinit var strAyat: String
    private lateinit var strKeterangan: String
    private lateinit var strAudio: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurahBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = SurahPresenter(this, this)
        setToolbar()

        modelSurah = Parcels.unwrap(intent.getParcelableExtra(Constant.DetailSurah))

        setUI()
    }

    override fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetTextI18n")
    override fun setUI() {
        setAction()
        strNomor = modelSurah.nomor ?: ""
        strNama = modelSurah.nama ?: ""
        strArti = modelSurah.arti ?: ""
        strType = modelSurah.type ?: ""
        strAyat = modelSurah.ayat ?: ""
        strAudio = modelSurah.audio ?: ""
        strKeterangan = modelSurah.keterangan ?: ""

        binding.fabStop.visibility = View.GONE
        binding.fabPlay.visibility = View.VISIBLE

        //Set text
        title = strNama
        binding.tvTitle.text = strNama
        binding.tvSubTitle.text = strArti
        binding.tvInfo.text = "$strType - $strAyat Ayat "

        binding.tvKet.text =
            Html.fromHtml(strKeterangan, Html.FROM_HTML_MODE_COMPACT)


        presenter.getSurah(strNomor)
    }

    override fun setAction() {
        val mediaPlayer = MediaPlayer()
        binding.fabPlay.setOnClickListener {
            try {
                mediaPlayer.setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                mediaPlayer.setDataSource(strAudio)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            binding.fabPlay.visibility = View.GONE
            binding.fabStop.visibility = View.VISIBLE
        }

        binding.fabStop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            binding.fabPlay.visibility = View.VISIBLE
            binding.fabStop.visibility = View.GONE
        }

        mediaPlayer.setOnCompletionListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            binding.fabPlay.visibility = View.VISIBLE
            binding.fabStop.visibility = View.GONE
        }
    }

    override fun showDataSuccess(data: MutableList<ModelAyat>) {
        binding.rvAyat.layoutManager = LinearLayoutManager(this)
        binding.rvAyat.setHasFixedSize(true)
        binding.rvAyat.adapter = SurahAdapter(data)
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