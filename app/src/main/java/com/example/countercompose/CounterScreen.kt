
package com.example.countercompose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countercompose.ui.theme.CounterComposeTheme

@Composable
fun CounterApp(
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = viewModel(),
    dataDiriViewModel: DataDiriViewModel = viewModel()
) {

    // Mengambil state counter
    val counter by viewModel.counter

    // Mengambil state nama dan umur
    val nama by dataDiriViewModel.nama
    val umur by dataDiriViewModel.umur

    // State untuk perpindahan halaman
    var showDataDiri by rememberSaveable {
        mutableStateOf(false)
    }

    // Tombol back pada HP kembali ke halaman Counter
    BackHandler(enabled = showDataDiri) {
        showDataDiri = false
    }

    if (!showDataDiri) {

        // Halaman pertama: Counter
        CounterScreen(
            counter = counter,
            onIncrement = { viewModel.increment() },
            onDecrement = { viewModel.decrement() },
            onReset = { viewModel.reset() },
            onNext = {
                showDataDiri = true
            },
            modifier = modifier
        )

    } else {

        // Halaman kedua: Data Diri
        DataDiriScreen(
            nama = nama,
            umur = umur,
            onNamaChange = {
                dataDiriViewModel.updateNama(it)
            },
            onUmurChange = {
                dataDiriViewModel.updateUmur(it)
            },
            onBack = {
                showDataDiri = false
            },
            modifier = modifier
        )
    }
}


@Composable
fun CounterScreen(
    counter: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onReset: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Counter App",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CounterDisplay(
            value = counter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        CounterButtons(
            onDecrement = onDecrement,
            onIncrement = onIncrement,
            onReset = onReset,
            onNext = onNext
        )
    }
}


@Composable
fun CounterDisplay(
    value: Int,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.background(
            color = Color(0xFFF5F5F5),
            shape = RoundedCornerShape(16.dp)
        ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "Nilai Counter",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )

        Text(
            text = value.toString(),
            fontSize = 120.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun CounterButtons(
    onDecrement: () -> Unit = {},
    onIncrement: () -> Unit = {},
    onReset: () -> Unit = {},
    onNext: () -> Unit = {}
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = onDecrement,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF44336)
                )
            ) {

                Icon(
                    painter = painterResource(R.drawable.ic_remove),
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )

                Spacer(Modifier.width(ButtonDefaults.IconSpacing))

                Text("KURANG")
            }

            Button(
                onClick = onIncrement,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {

                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )

                Spacer(Modifier.width(ButtonDefaults.IconSpacing))

                Text("TAMBAH")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tombol RESET
        Button(
            onClick = onReset,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_refresh),
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )

            Spacer(Modifier.width(ButtonDefaults.IconSpacing))

            Text("RESET")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tombol NEXT
        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF673AB7)
            )
        ) {

            Text("NEXT")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CounterScreenPreview() {

    CounterComposeTheme {

        CounterScreen(
            counter = 7,
            onIncrement = {},
            onDecrement = {},
            onReset = {},
            onNext = {}
        )
    }
}
