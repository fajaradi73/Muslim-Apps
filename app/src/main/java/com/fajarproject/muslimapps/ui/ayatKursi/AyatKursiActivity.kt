package com.fajarproject.muslimapps.ui.ayatKursi

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.fajarproject.muslimapps.databinding.ActivityAyatKursiBinding
import com.fajarproject.muslimapps.util.Util

class AyatKursiActivity : AppCompatActivity(), AyatKursiView {
    private lateinit var binding: ActivityAyatKursiBinding
    private lateinit var presenter: AyatKursiPresenter
    private var strTafsir = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAyatKursiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        presenter = AyatKursiPresenter(this)
        presenter.getData()
        setAction()
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
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setAction() {
        binding.fabTafsir.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Tafsir Ayat Kursi :")
            builder.setMessage(
                Html.fromHtml(strTafsir, Html.FROM_HTML_MODE_LEGACY).toString()
            )
            builder.setCancelable(true)

            builder.setPositiveButton("Tutup") { dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.show()

            val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            button.setTextColor(Color.BLACK)
            button.isAllCaps = false
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showDataSuccess(data: HashMap<String, String>) {
        binding.txtAyat.text = data["arabic"]
        binding.txtLatin.text = data["latin"]
        binding.txtTerjemahan.text = "Terjemahan : " + data["translation"]
        strTafsir = data["tafsir"].toString()
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