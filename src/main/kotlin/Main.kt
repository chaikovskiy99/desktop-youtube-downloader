import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.StringBuilder
import java.nio.file.Files
import java.nio.file.StandardCopyOption

@Composable
@Preview
fun App() {

    MaterialTheme {
        var input by remember { mutableStateOf("https://www.youtube.com/watch?v=ROOq2cXtupo") }
        var loading by remember { mutableStateOf(false) }
        val formatLists = remember { mutableStateOf(emptyList<Map<String, String>>()) }
        var process: Process? by remember { mutableStateOf(null) }
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TextField(
                input,
                onValueChange = { input = it },
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth()
            )

            Spacer(Modifier.size(0.dp, 16.dp))
            val scope = rememberCoroutineScope()
            val downloadOutput = remember { mutableStateOf(emptyList<String>()) }
            var errorOut by remember { mutableStateOf(emptyList<String>()) }
            when (loading) {
                true -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                false -> {
                    if (formatLists.value.isNotEmpty()) {
                        Row(Modifier.fillMaxWidth().padding(8.dp)) {
                            LazyColumn(
                                Modifier.weight(1f).padding(4.dp)
                                    .background(Color.LightGray)
                            ) {
                                items(formatLists.value) {
                                    Button(onClick = {
                                        process?.destroyForcibly()
                                        scope.launch {
                                            launch(Dispatchers.IO) {
                                                download(it["id"]!!, input) { out, err ->
                                                    println(out)
                                                    println(errorOut)
                                                    downloadOutput.value += out
//                                                    errorOut = err
                                                }
                                            }
                                        }
                                    }) {
                                        Text("${it["id"]} format: ${it["ext"]} size: ${it["fileSize"]}")
                                    }
                                }
                            }
                            Column(Modifier.weight(1f).background(Color.Cyan)) {
                                downloadOutput.value.forEach {
                                    Text(it)
                                }
//                                Text(errorOut)
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(input) {
            loading = true
            withContext(Dispatchers.IO) {
                val resource = {}.javaClass.getResource("/yt-dlp")
                val tempFile = File.createTempFile("temp", ".py")
                if (resource != null) {
                    Files.copy(resource.openStream(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
                }
                val processBuilder = ProcessBuilder("python3", tempFile.absolutePath, "-F", input)
                process = processBuilder.start()
                val output = process?.inputStream?.bufferedReader()?.readText()
                val error = process?.errorStream?.bufferedReader()?.readText()

//                println(output)
                output!!.lineSequence()
                    .drop(3)
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
                    .filter { line ->
                        line.contains("360p") || line.contains("480p") || line.contains("720p") || line.contains("1080p")
                                || line.contains("1440p") || line.contains("2160p")
                    }
                    .map {
                        val line = it.replace("""\s+""".toRegex(), " ")
                            .replace("│", "")
                            .split(" ")
                        println(line)
                        mapOf(
                            "id" to line[0],
                            "ext" to line[1],
                            "fileSize" to line[5].ifEmpty { line[6] }
                        )
                    }
                    .toList().also {
                        formatLists.value = it
                    }
                println(error)
            }
            loading = false
        }
    }
}

fun download(id: String, url: String, callBack: (output: String, err: String) -> Unit) {
    val resource = {}.javaClass.getResource("/yt-dlp")
    val tempFile = File.createTempFile("temp", ".py")
    if (resource != null) {
        Files.copy(resource.openStream(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING)
    }
    val processBuilder = ProcessBuilder("python3", tempFile.absolutePath, "-f ${id}+140", url)
    processBuilder.directory(File(System.getProperty("user.home")))
    val process = processBuilder.start()
//    val output = process.inputStream.bufferedReader().readText()
    val error = process.errorStream.bufferedReader().readText()
    process.inputStream.bufferedReader().use { reader ->
        var line = reader.readLine()
        while (line != null) {
            callBack(line, "")
            line = reader.readLine()
        }
    }

}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

