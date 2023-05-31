package ppm.b.kelompok4.tokoelektronik.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ppm.b.kelompok4.tokoelektronik.ui.theme.Purple700
import ppm.b.kelompok4.tokoelektronik.ui.theme.Teal200
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


@Composable
fun FormPencatatanKomputer(navController : NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else
        "Simpan"
    val viewModel = hiltViewModel<PengelolaanKomputerViewModel>()
    val scope = rememberCoroutineScope()
    val merk = remember { mutableStateOf(TextFieldValue("")) }
    val jenis = remember { mutableStateOf(TextFieldValue("")) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val dapatDiupgrade = remember { mutableStateOf(0) }
    val spesifikasi = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Merk") },
            value = merk.value,
            onValueChange = {
                merk.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Jenis") },
            value = jenis.value,
            onValueChange = {
                jenis.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Harga") },
            value = harga.value,
            onValueChange = {
                harga.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "Rp. 0") }
        )

        Row(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            RadioButton(
                selected = dapatDiupgrade.value == 0,
                onClick = { dapatDiupgrade.value = 0 },
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Tidak",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(15.dp)
                    .weight(4f)
            )
            RadioButton(
                selected = dapatDiupgrade.value == 1,
                onClick = { dapatDiupgrade.value = 1 },
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Ya",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(15.dp)
                    .weight(4f)
            )
        }

        OutlinedTextField(
            label = { Text(text = "Spesifikas") },
            value = spesifikasi.value,
            onValueChange = {
                spesifikasi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "XXXXX") }
        )

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (id == null) {
                    scope.launch {
                        viewModel.insert(merk.value.text, jenis.value.text, Integer.parseInt(harga.value.text), dapatDiupgrade.value, spesifikasi.value.text)
                    }
                } else {
                    scope.launch {
                        viewModel.update(id, merk.value.text, jenis.value.text, Integer.parseInt(harga.value.text), dapatDiupgrade.value, spesifikasi.value.text)
                    }
                }
                navController.navigate("pengelolaan-komputer")
            }, colors = loginButtonColors) {
                Text(
                    text =  buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                merk.value = TextFieldValue("")
                jenis.value = TextFieldValue("")
                harga.value = TextFieldValue("")
                spesifikasi.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }

    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { Komputer ->
                Komputer?.let {
                    merk.value = TextFieldValue(Komputer.merk)
                    jenis.value = TextFieldValue(Komputer.jenis)
                    harga.value = TextFieldValue(Komputer.harga.toString())
                    dapatDiupgrade.value = Komputer.dapat_diupgrade
                    spesifikasi.value = TextFieldValue(Komputer.spesifikasi)
                }
            }
        }
    }
}