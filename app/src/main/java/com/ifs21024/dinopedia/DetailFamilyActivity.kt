package com.ifs21024.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21024.dinopedia.databinding.ActivityDetailFamilyBinding
import com.ifs21024.dinopedia.databinding.ActivityMainBinding

class DetailFamilyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFamilyBinding
    private var family: Family? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFamilyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILY,
                Family::class.java)
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
        // Menambahkan onClickListener untuk buttonDetail
        binding.buttonDetail.setOnClickListener {
            // Membuat intent untuk beralih ke activity TheroMain
            val intent = Intent(this@DetailFamilyActivity, TheroMain::class.java)
            // Memulai activity TheroMain
            startActivity(intent)
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
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_FAMILY = "extra_family"
    }



}