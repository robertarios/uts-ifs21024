package com.ifs21024.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Family(
    var name: String,
    var image: Int,
    var deskripsi: String,
    var periode: String,
    var karakteristik: String,
    var habitat: String,
    var perilaku: String,
    var klasifikasi: String,
    var startIndex: Int,
    var endIndex: Int,

) : Parcelable