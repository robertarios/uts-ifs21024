package com.ifs21024.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21024.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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
        val dataName = resources.getStringArray(R.array.nama_dino_family)
        val dataImage = resources.obtainTypedArray(R.array.image_dino_family)
        val dataDescription = resources.getStringArray(R.array.deskripsi_dino_family)
        val dataPeriode = resources.getStringArray(R.array.periode_dino_family)
        val dataKarakteristik = resources.getStringArray(R.array.karakteristik_dino_family)
        val dataHabitat = resources.getStringArray(R.array.habitat_dino_family)
        val dataPerilaku = resources.getStringArray(R.array.perilaku_dino_family)
        val dataKlasifikasi = resources.getStringArray(R.array.klasifikasi_dino_family)

        val listFamily = ArrayList<Family>()
        for (i in dataName.indices) {
            val family = Family(dataName[i],
                dataImage.getResourceId(i, -1), dataDescription[i],
                dataPeriode[i], dataHabitat[i], dataKarakteristik[i], dataHabitat[i],
                dataPerilaku[i])
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
        val intentWithData = Intent(this@MainActivity,
            DetailFamilyActivity::class.java)
        intentWithData.putExtra(DetailFamilyActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
}