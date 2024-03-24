package com.ifs21024.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        setSupportActionBar(binding.toolbar) // Atur toolbar sebagai action bar

        supportActionBar?.apply {
            title = "Daftar Family" // Atur judul pada app bar
        }

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
        val startIndex = resources.getStringArray(R.array.start_dino_array)
        val endIndex = resources.getStringArray(R.array.end_dino_array)

        val listFamily = ArrayList<Family>()
        for (i in dataName.indices) {
            val family = Family(dataName[i],
                dataImage.getResourceId(i, -1), dataDescription[i],
                dataPeriode[i], dataKarakteristik[i], dataHabitat[i],
                dataPerilaku[i], dataKlasifikasi[i], startIndex[i].toInt(), endIndex[i].toInt())
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
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_about -> {
                val intent = Intent(this, UserDetail::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}