fun main() {
    fun part1(input: List<String>): Int {

        val digits = mutableListOf<Int>()
        var firstDigit: String = "0"
        var secondDigit: String = "0"

        for (line in input) {
            for (char in line) {
                if (char.isDigit()) {
                    if (firstDigit == "0") {
                        firstDigit = char.toString()
                    } else {
                        secondDigit = char.toString()
                    }
                }
            }
            if (secondDigit == "0") {
                secondDigit = firstDigit
            }
            digits.add((firstDigit + secondDigit).toInt())
            firstDigit = "0"
            secondDigit = "0"
        }
        println(digits)
        return digits.sum()
    }

    fun part2(input: List<String>): Int {
        val digits = mutableListOf<Int>()
        var firstDigit: String = "0"
        var secondDigit: String = "0"

        for (line in input) {
            for (char in line) {
                if (char.isDigit()) {
                    if (firstDigit == "0") {
                        firstDigit = char.toString()
                    } else {
                        secondDigit = char.toString()
                    }
                }
            }
            if (secondDigit == "0") {
                secondDigit = firstDigit
            }
            digits.add((firstDigit + secondDigit).toInt())
            firstDigit = "0"
            secondDigit = "0"
        }
        println(digits)
        return digits.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

     val input = readInput("Day01")
     part2(input).println()
}
