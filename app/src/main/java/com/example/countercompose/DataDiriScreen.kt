
package com.example.countercompose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DataDiriScreen(
    nama: String,
    umur: String,
    onNamaChange: (String) -> Unit,
    onUmurChange: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Judul halaman
        Text(
            text = "Data Diri",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Form input nama
        OutlinedTextField(
            value = nama,
            onValueChange = onNamaChange,
            label = {
                Text("Nama")
            },
            placeholder = {
                Text("Masukkan nama Anda")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Form input umur
        OutlinedTextField(
            value = umur,
            onValueChange = onUmurChange,
            label = {
                Text("Umur")
            },
            placeholder = {
                Text("Masukkan umur Anda")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Ringkasan hasil input
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Ringkasan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Nama saya \"$nama\" dan umur saya \"$umur\", Salam kenal!",
                    fontSize = 16.sp
                )
            }
        }

        // Memberikan ruang kosong
        Spacer(modifier = Modifier.weight(1f))

        // Tombol kembali ke Counter
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("KEMBALI")
        }
    }
}
