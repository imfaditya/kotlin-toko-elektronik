package ppm.b.kelompok4.tokoelektronik.model

//enum class SistemOperasiSmartphone {
//    Android,
//    iOS
//}

data class Smartphone(
    val model: String,
    val warna: String,
    val storage: Int,
    val tanggalRilis: String,
    val sistemOperasi: String
)
