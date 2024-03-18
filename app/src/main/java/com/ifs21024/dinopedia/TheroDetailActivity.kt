package com.ifs21024.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21024.dinopedia.databinding.ActivityTheroDetailBinding
import com.ifs21024.dinopedia.databinding.ActivityTheroMainBinding

class TheroDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTheroDetailBinding
    private var thero: Thero? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTheroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_THERO,
                Thero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_THERO)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (thero != null) {
            supportActionBar?.title = "Thero ${thero!!.name}"
            loadData(thero!!)
        } else {
            finish()
        }
    }

    private fun loadData(thero: Thero) {
        binding.ivDetailImageT.setImageResource(thero.image)
        binding.tvDetailNameT.text = thero.name
        binding.tvDetailDeskripsiT.text = thero.deskripsi
        binding.tvDetailKelompok.text = thero.kelompok
        binding.tvDetailKarakteristik.text = thero.karakteristik
        binding.tvDetailHabitatT.text = thero.habitat
        binding.tvDetailMakanan.text = thero.makanan
        binding.tvDetailPanjang.text = thero.panjang
        binding.tvDetailTinggi.text = thero.tinggi
        binding.tvDetailBobot.text = thero.bobot
        binding.tvDetailKelemahan.text = thero.kelemahan
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
        const val EXTRA_THERO= "extra_thero"
    }

}