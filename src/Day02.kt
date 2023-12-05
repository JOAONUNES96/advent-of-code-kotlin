fun main() {
    fun part1(input: List<String>): Int {
        val maxRed = 12
        val maxGreen = 13
        val maxBlue = 14

        var sumOfGameIds = 0

        for (line in input) {
            val parts = line.split(": ")
            val gameId = parts[0].removePrefix("Game ").toInt()
            val gameDataSets = parts[1].split(";").map { it.trim() }

            var gamePossible = true

            for (gameDataSet in gameDataSets) {
                var red = 0
                var blue = 0
                var green = 0

                gameDataSet.split(",").forEach {
                    val (count, color) = it.trim().split(" ")
                    when (color) {
                        "red" -> red = count.toInt()
                        "blue" -> blue = count.toInt()
                        "green" -> green = count.toInt()
                    }
                }

                if (red > maxRed || blue > maxBlue || green > maxGreen) {
                    gamePossible = false
                    break
                }
            }

            if (gamePossible) {
                sumOfGameIds += gameId
            }
        }

        println(sumOfGameIds)
        return sumOfGameIds
    }

    fun part2(input: List<String>): Int {
        var totalPower = 0

        for (line in input) {
            val gameDataSets = line.split(": ")[1].split(";").map { it.trim() }

            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0

            for (gameDataSet in gameDataSets) {
                var red = 0
                var blue = 0
                var green = 0

                gameDataSet.split(",").forEach {
                    val (count, color) = it.trim().split(" ")
                    when (color) {
                        "red" -> red = count.toInt()
                        "blue" -> blue = count.toInt()
                        "green" -> green = count.toInt()
                    }
                }

                maxRed = maxOf(maxRed, red)
                maxGreen = maxOf(maxGreen, green)
                maxBlue = maxOf(maxBlue, blue)
            }

            val power = maxRed * maxGreen * maxBlue
            totalPower += power
        }

        return totalPower
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part2(input).println()
}
