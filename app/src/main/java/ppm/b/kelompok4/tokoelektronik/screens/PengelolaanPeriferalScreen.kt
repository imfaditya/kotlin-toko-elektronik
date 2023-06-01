package ppm.b.kelompok4.tokoelektronik.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.room.Room
import ppm.b.kelompok4.tokoelektronik.model.Smartphone
import ppm.b.kelompok4.tokoelektronik.persistences.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ppm.b.kelompok4.tokoelektronik.model.Periferal

@Composable
fun PengelolaanPeriferalScreen(navController : NavHostController, modifier: Modifier = Modifier, snackbarHostState: SnackbarHostState) {

    val viewModel = hiltViewModel<PengelolaanPeriferalViewModel>()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    val items: List<Periferal> by viewModel.list.observeAsState(initial = listOf())

    Column(modifier = modifier.fillMaxWidth()) {
        Button(onClick = {
            navController.navigate("tambah-pengelolaan-periferal")
        }) {
            Text(text = "Tambah")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth().clickable {
                        navController.navigate("edit-pengelolaan-periferal/${item.id}")
                    }) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama", fontSize = 14.sp)
                        Text(
                            text = item.nama, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Harga", fontSize = 14.sp)
                        Text(
                            text = "Rp. ${item.harga}", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Deskripsi", fontSize = 14.sp)
                        Text(
                            text = item.deskripsi, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Jenis", fontSize = 14.sp)
                        Text(
                            text = item.jenis, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
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
    viewModel.toast.observe(LocalLifecycleOwner.current) {
        scope.launch {
            snackbarHostState.showSnackbar(it, actionLabel =
            "Tutup", duration = SnackbarDuration.Long)
        }
    }
}