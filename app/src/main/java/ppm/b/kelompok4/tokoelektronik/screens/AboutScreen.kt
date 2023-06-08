package ppm.b.kelompok4.tokoelektronik.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().fillMaxHeight()) {
        Text(
            text = "Imam Faraz Aditya",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Dik Dik Nur Illahi",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Farhat Abdurachman Aldjaidi",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Ray Gineung Pratidina Zunaedi",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Reza Marchyana",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}