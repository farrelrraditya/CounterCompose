
package com.example.countercompose

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DataDiriViewModel : ViewModel() {

    // State untuk menyimpan nama
    private val _nama = mutableStateOf("")
    val nama: State<String> = _nama

    // State untuk menyimpan umur
    private val _umur = mutableStateOf("")
    val umur: State<String> = _umur

    // Fungsi untuk mengubah nama
    fun updateNama(namaBaru: String) {
        _nama.value = namaBaru
    }

    // Fungsi untuk mengubah umur
    fun updateUmur(umurBaru: String) {
        _umur.value = umurBaru.filter { it.isDigit() }
    }
}
