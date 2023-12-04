fun main() {
//    val t = text.lineSequence().drop(3).map { it.trim() }.filter { it.isNotEmpty() }.map {
//        line ->
////        val parts = line.split("\\s+".toRegex())
//        val regex = """(\d+)\s+(\w+)\s+(.+?)\s+(\d+)\s+│\s+(.+?)\s+(\d+k)\s+https │ (.+?)\s+(\w+\.\w+\.\w+)\s+(\d+k)\s+(\d+k)\s+\[(\w+)\]\s+(.+),\s+(.+)""".toRegex()
////        println(parts)
////        mapOf(
////            "id" to parts[0],
////            "ext" to parts[1],
////            "size" to parts.getOrNull(2),
////            "fps" to parts[3],
////            "size" to parts[5],
////            "type" to (parts.getOrNull(11) ),
////            "quality" to parts.getOrNull(13),
////            "media_format" to parts.getOrNull(14)
////        )
//        val matchResult = regex.matchEntire(line)
//        if (matchResult != null) {
//            val id = matchResult.groupValues[1]
//            val ext = matchResult.groupValues[2]
//            val type1 = matchResult.groupValues[3]
//            val fps = matchResult.groupValues[4]
//            val filesize = matchResult.groupValues[5]
//            val tbr = matchResult.groupValues[6]
//            val type2 = matchResult.groupValues[7]
//            val vcodec = matchResult.groupValues[8]
//            val vbr = matchResult.groupValues[9]
//            val abr = matchResult.groupValues[10]
//            val asr = matchResult.groupValues[11]
//            val moreInfo1 = matchResult.groupValues[12]
//            val moreInfo2 = matchResult.groupValues[13]
//            println("id: $id, ext: $ext, type1: $type1, fps: $fps, filesize: $filesize, tbr: $tbr, type2: $type2, vcodec: $vcodec, vbr: $vbr, abr: $abr, asr: $asr, moreInfo1: $moreInfo1, moreInfo2: $moreInfo2")
//        } else {
//            println("No match found")
//        }
//    }
//        .toList()


//    val regex = """(\w+)\s+(\w+)\s+(.+?)\s+(\d+)?\s+│\s+(.+?)\s+(\d+k)?\s+https │ (.+?)\s+(\w+\.\w+\.\w+)?\s+(\d+k)?\s+(\d+k)?\s+\[(\w+)\]\s+(.+),\s+(.+)""".toRegex()
//    val regex = """(\w+)\s+(\w+)\s+(\w+\s?\w+)\s+(\d+)?\s+(\d+)?\s+\W+(\d+.\d+\w+)?\s+(\d+k)?\s+(\w+)\s+|\s+(\w+.+\w+)\s+""".toRegex()
    val regex = """(\w+)\s+.+""".toRegex()
//        val regex = """(\w+)\s+""".toRegex()
//    text.lineSequence()
//        .drop(3)
//        .map { it.trim() }
//        .filter { it.isNotEmpty() }
//        .filter { line ->
//            line.contains("360p") || line.contains("480p") || line.contains("720p") || line.contains("1080p")
//                || line.contains("1440p") || line.contains("2160p")
//        }
//        .map {
//            val line = it.replace("""\s+""".toRegex(), " ")
//                .replace("│", "")
//                .split(" ")
//            println(line)
//            mapOf(
//                "id" to line[0],
//                "ext" to line[1],
//                "fileSize" to line[5].ifEmpty { line[6] }
//            )
//        }
//        .toList()
//        .forEach { m->
//            println(m)
//        }
}

const val text = "[info] Available formats for ROOq2cXtupo:\n" +
        "ID  EXT   RESOLUTION FPS CH │   FILESIZE   TBR PROTO │ VCODEC          VBR ACODEC      ABR ASR MORE INFO\n" +
        "───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n" +
        "sb3 mhtml 48x27        0    │                  mhtml │ images                                  storyboard\n" +
        "sb2 mhtml 80x45        0    │                  mhtml │ images                                  storyboard\n" +
        "sb1 mhtml 160x90       0    │                  mhtml │ images                                  storyboard\n" +
        "sb0 mhtml 320x180      0    │                  mhtml │ images                                  storyboard\n" +
        "233 mp4   audio only        │                  m3u8  │ audio only          unknown             [en] Default\n" +
        "234 mp4   audio only        │                  m3u8  │ audio only          unknown             [en] Default\n" +
        "599 m4a   audio only      2 │    1.77MiB   31k https │ audio only          mp4a.40.5   31k 22k [en] ultralow, m4a_dash\n" +
        "600 webm  audio only      2 │    1.98MiB   34k https │ audio only          opus        34k 48k [en] ultralow, webm_dash\n" +
        "139 m4a   audio only      2 │    2.81MiB   49k https │ audio only          mp4a.40.5   49k 22k [en] low, m4a_dash\n" +
        "249 webm  audio only      2 │    2.89MiB   50k https │ audio only          opus        50k 48k [en] low, webm_dash\n" +
        "250 webm  audio only      2 │    3.71MiB   64k https │ audio only          opus        64k 48k [en] low, webm_dash\n" +
        "140 m4a   audio only      2 │    7.45MiB  129k https │ audio only          mp4a.40.2  129k 44k [en] medium, m4a_dash\n" +
        "251 webm  audio only      2 │    6.73MiB  117k https │ audio only          opus       117k 48k [en] medium, webm_dash\n" +
        "597 mp4   256x144     12    │    2.06MiB   36k https │ avc1.4d400b     36k video only          144p, mp4_dash\n" +
        "602 mp4   256x144     12    │ ~  5.68MiB   96k m3u8  │ vp09.00.10.08   96k video only\n" +
        "598 webm  256x144     12    │    1.85MiB   32k https │ vp9             32k video only          144p, webm_dash\n" +
        "269 mp4   256x144     24    │ ~ 10.37MiB  176k m3u8  │ avc1.4D400C    176k video only\n" +
        "160 mp4   256x144     24    │    4.53MiB   79k https │ avc1.4D400C     79k video only          144p, mp4_dash\n" +
        "603 mp4   256x144     24    │ ~ 10.57MiB  179k m3u8  │ vp09.00.11.08  179k video only\n" +
        "278 webm  256x144     24    │    4.28MiB   74k https │ vp09.00.11.08   74k video only          144p, webm_dash\n" +
        "229 mp4   426x240     24    │ ~ 18.93MiB  321k m3u8  │ avc1.4D4015    321k video only\n" +
        "133 mp4   426x240     24    │   10.64MiB  185k https │ avc1.4D4015    185k video only          240p, mp4_dash\n" +
        "604 mp4   426x240     24    │ ~ 15.47MiB  262k m3u8  │ vp09.00.20.08  262k video only\n" +
        "242 webm  426x240     24    │    8.67MiB  151k https │ vp09.00.20.08  151k video only          240p, webm_dash\n" +
        "230 mp4   640x360     24    │ ~ 42.01MiB  713k m3u8  │ avc1.4D401E    713k video only\n" +
        "134 mp4   640x360     24    │   18.79MiB  327k https │ avc1.4D401E    327k video only          360p, mp4_dash\n" +
        "18  mp4   640x360     24  2 │   24.35MiB  423k https │ avc1.42001E         mp4a.40.2       44k [en] 360p\n" +
        "605 mp4   640x360     24    │ ~ 28.01MiB  475k m3u8  │ vp09.00.21.08  475k video only\n" +
        "243 webm  640x360     24    │   14.95MiB  260k https │ vp09.00.21.08  260k video only          360p, webm_dash\n" +
        "231 mp4   854x480     24    │ ~ 75.85MiB 1286k m3u8  │ avc1.4D401E   1286k video only\n" +
        "135 mp4   854x480     24    │   42.07MiB  731k https │ avc1.4D401E    731k video only          480p, mp4_dash\n" +
        "606 mp4   854x480     24    │ ~ 41.42MiB  702k m3u8  │ vp09.00.30.08  702k video only\n" +
        "244 webm  854x480     24    │   26.53MiB  461k https │ vp09.00.30.08  461k video only          480p, webm_dash\n" +
        "22  mp4   1280x720    24  2 │ ≈ 89.09MiB 1511k https │ avc1.64001F         mp4a.40.2       44k [en] 720p\n" +
        "232 mp4   1280x720    24    │ ~123.12MiB 2088k m3u8  │ avc1.64001F   2088k video only\n" +
        "136 mp4   1280x720    24    │   79.53MiB 1383k https │ avc1.64001F   1383k video only          720p, mp4_dash\n" +
        "609 mp4   1280x720    24    │ ~ 65.51MiB 1111k m3u8  │ vp09.00.31.08 1111k video only\n" +
        "247 webm  1280x720    24    │   44.22MiB  769k https │ vp09.00.31.08  769k video only          720p, webm_dash\n" +
        "270 mp4   1920x1080   24    │ ~220.09MiB 3733k m3u8  │ avc1.640028   3733k video only\n" +
        "137 mp4   1920x1080   24    │  153.04MiB 2661k https │ avc1.640028   2661k video only          1080p, mp4_dash\n" +
        "614 mp4   1920x1080   24    │ ~ 96.15MiB 1631k m3u8  │ vp09.00.40.08 1631k video only\n" +
        "248 webm  1920x1080   24    │   75.72MiB 1316k https │ vp09.00.40.08 1316k video only          1080p, webm_dash"