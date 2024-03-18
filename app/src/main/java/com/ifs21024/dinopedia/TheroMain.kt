package com.ifs21024.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21024.dinopedia.databinding.ActivityMainBinding

class TheroMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataFamily = ArrayList<Family>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFamily.setHasFixedSize(false)
        dataFamily.addAll(getListFamily())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListFamily(): ArrayList<Family> {
        val dataNameT = resources.getStringArray(R.array.nama_Theropoda)
        val dataImage = resources.obtainTypedArray(R.array.image_dino_family)
        val dataDescriptionT = resources.getStringArray(R.array.deskripsi_Theropoda)
        val dataKelompok = resources.getStringArray(R.array.kelompok_Theropoda)
        val dataKarakteristikT = resources.getStringArray(R.array.karakteristik_dino_family)
        val dataHabitatT = resources.getStringArray(R.array.habitat_Theropoda)
        val dataMakanan = resources.getStringArray(R.array.makanan_Theropoda)
        val dataPanjang = resources.getStringArray(R.array.panjang_Theropoda)

        val listFamily = ArrayList<Family>()
        for (i in dataNameT.indices) {
            val family = Family(dataNameT[i],
                dataImage.getResourceId(i, -1), dataDescriptionT[i],
                dataKelompok[i], dataHabitatT[i], dataKarakteristikT[i], dataMakanan[i],
                dataPanjang[i],)
            listFamily.add(family)
        }
        return listFamily
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamily.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFamily.layoutManager =
                LinearLayoutManager(this)
        }

        val listFamilyAdapter = ListFamilyAdapter(dataFamily)
        binding.rvFamily.adapter = listFamilyAdapter
        listFamilyAdapter.setOnItemClickCallback(object :
            ListFamilyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Family) {
                showSelectedFamily(data)
            }
        })
    }

    private fun showSelectedFamily(family: Family) {
        val intentWithData = Intent(this@TheroMain,
            DetailFamilyActivity::class.java)
        intentWithData.putExtra(DetailFamilyActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
}