import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner

var duringShellPackage = System.getProperty("user.home")!!
var zipHomeDir: String? = null
val inp = Scanner(System.`in`)

fun main() {

    charset("UTF-16")

    println(File.separator)

    println(
        "VShell written by Savcheg on Kotlin 1.5.3\n" +
                "------------------------------------------"
    )
    while (true) {
        print("$duringShellPackage || vshell > ")
        var input = inp.nextLine().split(" ").toMutableList()
        if (input.isEmpty()) {
            println(
                "${input[0]} no such command\n" +
                        "   pls repeat"
            )
            continue
        }
        when (input[0]) {
            "open" -> openZipCommand(input)
            "pwd" -> pwdCommand(input)
            "ls" -> lsCommand(input)
            "cd" -> cdCommand(input)
            "cat" -> catCommand(input)
            "help" -> helpCommand(input)
            else -> {
                println(
                    "${input[0]} no such command\n" +
                            "   pls repeat"
                )
                continue
            }
        }
    }
}

fun helpCommand(commandList: MutableList<String>) {
    println("Мне лень сейчас это делать((")
}

fun openZipCommand(commandList: MutableList<String>) {
    zipHomeDir = ""
//    when (commandList.getOrNull(1)) {
//        is String ->
//    }
}

fun catCommand(commandList: MutableList<String>) {
    toOneString(commandList)
    when (checkFile(commandList[1])) {
        1 -> readFile(duringShellPackage + File.separator + commandList[1])
        2 -> readFile(commandList[1])
        0 -> println("no such file or unavailable")
    }
}

fun readFile(path: String) {
    val scanFile = Scanner(File(path))
    while (scanFile.hasNextLine())
        println(scanFile.nextLine())
}

fun cdCommand(commandList: MutableList<String>) {
    toOneString(commandList)
    when (commandList.getOrNull(1)) {
        null -> duringShellPackage = if (zipHomeDir == null) System.getProperty("user.home") else zipHomeDir!!
        is String -> goToDir(commandList[1])
    }
}

fun goToDir(path: String) {
    when (checkDir(path)) {
        1 -> duringShellPackage += File.separator + path
        2 -> duringShellPackage = path
        0 -> println("no such dir")
        -1 -> goBackDir()
    }
}

fun goBackDir() {
    duringShellPackage =
        duringShellPackage.subSequence(0, duringShellPackage.indexOfLast { it == File.separator.toCharArray()[0] })
            .toString()
}

fun checkDir(pathS: String): Int {
    if (Files.exists(Paths.get(duringShellPackage + File.separator + pathS)) && (Files.isDirectory(
            Paths.get(
                duringShellPackage + File.separator + pathS
            )
        ))
    )
        return 1
    if (Files.exists(Paths.get(pathS)) && (Files.isDirectory(Paths.get(pathS))))
        return 2
    if (pathS == "-")
        return -1
    return 0
}

fun checkFile(pathS: String): Int {
    val path = Paths.get(duringShellPackage + File.separator + pathS)
    if (Files.exists(path))
        return 1
    if (Files.exists(Paths.get(pathS)))
        return 2
    return 0
}

fun lsCommand(commandList: MutableList<String>) {
    val folder = File(duringShellPackage)
    for (fileEntry: File in folder.listFiles()) {
        println(fileEntry.name)
    }
}

fun pwdCommand(commandList: MutableList<String>) {
    println(duringShellPackage)
}

fun toOneString(input: MutableList<String>) {
    if (input.size > 1)
        for (i in input.size - 1 downTo 2) {
            input[1] += " " + input[i]
            input.removeLast()
        }
}