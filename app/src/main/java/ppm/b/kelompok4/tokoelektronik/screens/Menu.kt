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
    HOME(R.string.home, Icons.Default.Home, "home"),
    PENGELOLAAN_SAMPAH(R.string.pengelolaan_tokoElektronik, Icons.Default.List, "pengelolaan-tokoElektronik"),
    SETTING(R.string.setting, Icons.Default.Settings,
        "setting");
    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu
        {
            return when (resource) {
                R.string.home -> HOME
                R.string.pengelolaan_tokoElektronik ->
                    PENGELOLAAN_SAMPAH
                else -> SETTING
            }
        }
    }
}