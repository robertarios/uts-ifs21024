package com.ifs21024.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino(
    var name: String,
    var image: Int,
    var deskripsi: String,
    var karakteristik: String,
    var kelompok: String,
    var habitat: String,
    var makanan: String,
    var panjang: String,
    var tinggi: String,
    var bobot: String,
    var kelemahan: String,
) : Parcelable