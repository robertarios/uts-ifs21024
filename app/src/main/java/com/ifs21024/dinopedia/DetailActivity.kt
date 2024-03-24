package com.ifs21024.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21024.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var family: Family? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) // Atur toolbar sebagai action bar

        supportActionBar?.apply {
            title = "Detail Family" // Atur judul pada app bar
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol kembali
        }

        family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_FAMILY, Family::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (family != null) {
            supportActionBar?.title = "Family ${family!!.name}"
            loadData(family!!)
        } else {
            finish()
        }

        // Menambahkan onClickListener untuk buttonDino di dalam onCreate()
        binding.buttonDino.setOnClickListener {
            val intentWithData = Intent(this@DetailActivity, DinoMainActivity::class.java)
            intentWithData.putExtra(DinoMainActivity.EXTRA_FAMILY, family!!)
            startActivity(intentWithData)
        }
    }

    private fun loadData(family: Family) {
        binding.ivDetailImage.setImageResource(family.image)
        binding.tvDetailName.text = family.name
        binding.tvDetailDeskripsi.text = family.deskripsi
        binding.tvDetailPeriode.text = family.periode
        binding.tvDetailKarakteristik.text = family.karakteristik
        binding.tvDetailHabitat.text = family.habitat
        binding.tvDetailPerilaku.text = family.perilaku
        binding.tvDetailKlasifikasi.text = family.klasifikasi
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object{
        const val EXTRA_FAMILY ="extra_family"
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
