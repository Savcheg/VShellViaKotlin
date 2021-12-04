import java.util.zip.ZipEntry
import java.util.zip.ZipFile

fun main() {
    val zipFile = ZipFile("D:\\Files\\Arch.zip")
    var ent = zipFile.entries()
    var zipEntr = ArrayList<ZipEntry>()
    while (ent.hasMoreElements()) {
        zipEntr.add(ent.nextElement())
    }
    println(zipEntr)
}