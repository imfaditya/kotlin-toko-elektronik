package ppm.b.kelompok4.tokoelektronik.model

//enum class JenisKomputer {
//    Laptop,
//    Desktop,
//    AIO
//}

data class Komputer(
    val merk: String,
    val jenis: String,
    val harga: Int,
    val dapatDiupgrade: Boolean,
    val spesifikasi: String,
)
