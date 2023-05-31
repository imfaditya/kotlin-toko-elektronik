package ppm.b.kelompok4.tokoelektronik.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import ppm.b.kelompok4.tokoelektronik.R
enum class Menu (
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    PENGELOLAAN_KOMPUTER(R.string.pengelolaan_komputer, Icons.Default.List, "pengelolaan-komputer"),
    PENGELOLAAN_SMARTPHONE(R.string.pengelolaan_smartphone, Icons.Default.List, "pengelolaan-smartphone"),
    PENGELOLAAN_PERIFERAL(R.string.pengelolaan_periferal, Icons.Default.List, "pengelolaan-periferal");
    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu
        {
            return when (resource) {
                R.string.pengelolaan_komputer -> PENGELOLAAN_KOMPUTER
                R.string.pengelolaan_periferal -> PENGELOLAAN_PERIFERAL
                else -> PENGELOLAAN_SMARTPHONE
            }
        }
    }
}