package ppm.b.kelompok4.tokoelektronik.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ppm.b.kelompok4.tokoelektronik.model.Komputer
import ppm.b.kelompok4.tokoelektronik.model.Periferal

@Composable
fun PengelolaanKomputerScreen(navController : NavHostController, modifier: Modifier = Modifier, snackbarHostState: SnackbarHostState) {
    val viewModel = hiltViewModel<PengelolaanKomputerViewModel>()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val items: List<Komputer> by viewModel.listData.observeAsState(initial = listOf())

    Column(modifier = modifier.fillMaxWidth()) {
        Button(onClick = {
            navController.navigate("tambah-pengelolaan-komputer")
        }) {
            Text(text = "Tambah")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("edit-pengelolaan-komputer/" + item.id)
                    }
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Merk")
                        Text(text = item.merk, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Jenis")
                        Text(
                            text = item.jenis,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "harga")
                        Text(text = item.harga.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Dapat Di Upgrade")
                        val dapatDiUpgradeText = when (item.dapat_diupgrade) {
                            0 -> "Tidak"
                            else -> "Ya"
                        }
                        Text(text = dapatDiUpgradeText, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Spesifikasi")
                        Text(text = item.spesifikasi, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }

    LaunchedEffect(scope) {
        viewModel.loadItems()
    }

    viewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                viewModel.loadItems()
            }
        }
    }

//    viewModel.toast.observe(LocalLifecycleOwner.current) {
//        scope.launch {
//            snackbarHostState.showSnackbar(it, actionLabel =
//            "Tutup", duration = SnackbarDuration.Long)
//        }
//    }
}