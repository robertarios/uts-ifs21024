package com.ifs21024.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21024.dinopedia.databinding.ActivityDinoMainBinding

class DinoMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinoMainBinding
    private val dataDino = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) // Atur toolbar sebagai action bar

        supportActionBar?.apply {
            title = "Daftar Dino" // Atur judul pada app bar
            setDisplayHomeAsUpEnabled(true) // Menampilkan tombol kembali
        }

        binding.rvDino.setHasFixedSize(false)
        dataDino.addAll(getListDino())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {
        val family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_FAMILY, Family::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }

        val dataNameDino = resources.getStringArray(R.array.nama_Dino)
        val dataImage = resources.obtainTypedArray(R.array.image_dino)
        val dataDescriptionDino = resources.getStringArray(R.array.deskripsi_Dino)
        val dataKelompok = resources.getStringArray(R.array.kelompok_Dino)
        val dataKarakteristikDino = resources.getStringArray(R.array.karakteristik_Dino)
        val dataHabitatT = resources.getStringArray(R.array.habitat_Dino)
        val dataMakanan = resources.getStringArray(R.array.makanan_Dino)
        val dataPanjang = resources.getStringArray(R.array.panjang_Dino)
        val dataTinggi = resources.getStringArray(R.array.tinggi_Dino)
        val dataBobot = resources.getStringArray(R.array.bobot_Dino)
        val dataKelemahan = resources.getStringArray(R.array.kelemahan_Dino)

        val startIndex = family?.startIndex ?: 0
        val endIndex = family?.endIndex ?: dataNameDino.size

        val listDino = ArrayList<Dino>()
        for (i in startIndex..endIndex) {
            if (i < dataNameDino.size && i < dataImage.length() && i < dataDescriptionDino.size &&
                i < dataKelompok.size && i < dataKarakteristikDino.size && i < dataHabitatT.size &&
                i < dataMakanan.size && i < dataPanjang.size && i < dataTinggi.size &&
                i < dataBobot.size && i < dataKelemahan.size
            ) {
                val dino = Dino(
                    dataNameDino[i],
                    dataImage.getResourceId(i, -1),
                    dataDescriptionDino[i],
                    dataKelompok[i],
                    dataHabitatT[i],
                    dataKarakteristikDino[i],
                    dataMakanan[i],
                    dataPanjang[i],
                    dataTinggi[i],
                    dataBobot[i],
                    dataKelemahan[i]
                )
                listDino.add(dino)
            }
        }
        dataImage.recycle() // Recycle the TypedArray
        return listDino
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDino.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDino.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinoAdapter = ListDinoAdapter(dataDino)
        binding.rvDino.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@DinoMainActivity,
            DinoDetailActivity::class.java)
        intentWithData.putExtra(DinoDetailActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }
    companion object{
        const val EXTRA_FAMILY ="extra_family"
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}