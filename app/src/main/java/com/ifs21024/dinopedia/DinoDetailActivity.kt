package com.ifs21024.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21024.dinopedia.databinding.ActivityDinoDetailBinding

class DinoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinoDetailBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) // Atur toolbar sebagai action bar

        supportActionBar?.apply {
            title = "Detail Dino" // Atur judul pada app bar
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol kembali
        }

        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINO,
                Dino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Dino ${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    private fun loadData(dino: Dino) {
        binding.ivDetailImageDino.setImageResource(dino.image)
        binding.tvDetailNameDino.text = dino.name
        binding.tvDetailDeskripsiDino.text = dino.deskripsi
        binding.tvDetailKelompokDino.text = dino.kelompok
        binding.tvDetailKarakteristikDino.text = dino.karakteristik
        binding.tvDetailHabitatDino.text = dino.habitat
        binding.tvDetailMakananDino.text = dino.makanan
        binding.tvDetailPanjangDino.text = dino.panjang
        binding.tvDetailTinggiDino.text = dino.tinggi
        binding.tvDetailBobotDino.text = dino.bobot
        binding.tvDetailKelemahanDino.text = dino.kelemahan
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}