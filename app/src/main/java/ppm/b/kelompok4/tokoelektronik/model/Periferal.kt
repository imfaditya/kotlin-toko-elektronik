package ppm.b.kelompok4.tokoelektronik.model

enum class JenisPeriferal {

}

data class Periferal(
    val nama: String,
    val harga: Int,
    val deskripsi: String,
    val jenis: JenisPeriferal,
)
