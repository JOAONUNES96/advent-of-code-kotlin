import java.io.File

fun main() {
    fun part2(input: List<String>): Long? {
        val seeds = input[0].split("seeds: ")[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() }

        val seedToSoilMap = "src/Day05/seedToSoilMap.txt"
        val soilToFertilizerMap = "src/Day05/soilToFertilizerMap.txt"
        val fertilizerToWaterMap = "src/Day05/fertilizerToWaterMap.txt"
        val waterTolightMap = "src/Day05/waterTolightMap.txt"
        val lightToTemperatureMap = "src/Day05/lightToTemperatureMap.txt"
        val temperatureToHumidityMap = "src/Day05/temperatureToHumidityMap.txt"
        val humidityToLocationMap = "src/Day05/humidityToLocationMap.txt"

        fun addMapping(mapFile: String, input: List<String>, delimiter: String) {
            val file = File(mapFile)
            file.writeText("")
            var startProcessing = false

            file.bufferedWriter().use { writer ->
                for (line in input) {
                    if (line.contains(delimiter)) {
                        startProcessing = true
                        continue
                    }
                    if (startProcessing) {
                        if (line.isBlank()) break
                        val parts = line.split(" ").filter { it.isNotEmpty() }.map { it.trim().toLong() }
                        if (parts.size == 3) {
                            val (destStart, srcStart, length) = parts
                            for (i in 0 until length) {
                                writer.write("${srcStart + i} -> ${destStart + i}\n")
                            }
                        }
                    }
                }
            }
        }

        fun getMappingFromMapFile(mapFile: String, source: Long): Long {
            File(mapFile).useLines { lines ->
                lines.forEach { line ->
                    val parts = line.split(" -> ")
                    if (parts[0].toInt().toLong() == source) {
                        return parts[1].toLong()
                    }
                }
            }
            return source // Return the source number if no mapping is found
        }


        fun getSeedLocation(seeds: List<Long>, vararg mapFiles: String): Long? {
            var seedWithLowestLocation: Long? = null

            for (seed in seeds) {
                var current = seed
                mapFiles.forEach { mapFile ->
                    current = getMappingFromMapFile(mapFile, current)
                }

                if (seedWithLowestLocation == null || current < seedWithLowestLocation) {
                    seedWithLowestLocation = current
                }
            }
            println("seed with lowest location is $seedWithLowestLocation")
            return seedWithLowestLocation
        }

        addMapping(seedToSoilMap, input, "seed-to-soil map:")
        addMapping(soilToFertilizerMap, input, "soil-to-fertilizer map:")
        addMapping(fertilizerToWaterMap, input, "fertilizer-to-water map:")
        addMapping(waterTolightMap, input, "water-to-light map:")
        addMapping(lightToTemperatureMap, input, "light-to-temperature map:")
        addMapping(temperatureToHumidityMap, input, "temperature-to-humidity map:")
        addMapping(humidityToLocationMap, input, "humidity-to-location map:")
        return getSeedLocation(seeds, seedToSoilMap, soilToFertilizerMap, fertilizerToWaterMap, waterTolightMap, lightToTemperatureMap, temperatureToHumidityMap, humidityToLocationMap)
    }


    val input = readInput("Day05_test")
    part2(input).println()
}
