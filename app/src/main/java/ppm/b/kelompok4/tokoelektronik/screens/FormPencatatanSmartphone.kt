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
//import ppm.b.kelompok4.tokoelektronik.ui.theme.tokoelektronikTheme
import kotlinx.coroutines.launch
import ppm.b.kelompok4.tokoelektronik.model.Smartphone


@Composable
fun FormPencatatanSmartphone(navController : NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else
        "Simpan"
    val viewModel = hiltViewModel<PengelolaanSmartphoneViewModel>()
    val scope = rememberCoroutineScope()
    val model = remember { mutableStateOf(TextFieldValue("")) }
    val warna = remember { mutableStateOf(TextFieldValue("")) }
    val storage = remember { mutableStateOf(TextFieldValue("")) }
    val tanggalRilis = remember { mutableStateOf(TextFieldValue("")) }
    val sistemOperasi = remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Model") },
            value = model.value,
            onValueChange = {
                model.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Warna") },
            value = warna.value,
            onValueChange = {
                warna.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Storage") },
            value = storage.value,
            onValueChange = {
                storage.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "64") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Rilis") },
            value = tanggalRilis.value,
            onValueChange = {
                tanggalRilis.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "12 September 2023") }
        )
        OutlinedTextField(
            label = { Text(text = "Sistem Operasi") },
            value = sistemOperasi.value,
            onValueChange = {
                sistemOperasi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Lolipop") }
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
                        viewModel.insert(model.value.text, warna.value.text, Integer.parseInt(storage.value.text),
                        tanggalRilis.value.text, sistemOperasi.value.text)
                    }
                } else {
                    scope.launch {
                        viewModel.update(id, model.value.text, warna.value.text, Integer.parseInt(storage.value.text),
                            tanggalRilis.value.text, sistemOperasi.value.text)
                    }
                }
                navController.navigate("pengelolaan-smartphone")
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
                model.value = TextFieldValue("")
                warna.value = TextFieldValue("")
                storage.value = TextFieldValue("")
                tanggalRilis.value = TextFieldValue("")
                sistemOperasi.value = TextFieldValue("")
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
            viewModel.loadItem(id) { Smartphone ->
                Smartphone?.let {
                    model.value = TextFieldValue(Smartphone.model)
                    warna.value = TextFieldValue(Smartphone.warna)
                    storage.value = TextFieldValue(Smartphone.storage.toString())
                    tanggalRilis.value = TextFieldValue(Smartphone.tanggal_rilis)
                    sistemOperasi.value = TextFieldValue(Smartphone.sistem_operasi)
                }
            }
        }
    }
}