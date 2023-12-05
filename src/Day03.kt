fun main() {
    fun part1(input: List<String>): Int {
        var sumOfParts = 0
        val symbols = setOf('#', '$', '+', '*', '/', '"', '%', '@', '=', '-', '~', '!', '^', '&')
        val numRows = input.size
        val numCols = input[0].length
        val countedNumbers = mutableSetOf<Pair<Int, Int>>() // Track the starting position of counted numbers

        // Function to extract a number at a given position
        fun extractNumber(row: Int, col: Int): Pair<String, Pair<Int, Int>>? {
            if (!input[row][col].isDigit()) return null

            var number = input[row][col].toString()
            var startRow = row
            var startCol = col

            // Check left for multi-digit numbers
            while (startCol - 1 >= 0 && input[row][startCol - 1].isDigit()) {
                startCol--
                number = input[row][startCol] + number
            }

            // Check right for multi-digit numbers
            var endCol = col + 1
            while (endCol < numCols && input[row][endCol].isDigit()) {
                number += input[row][endCol]
                endCol++
            }

            return number to (startRow to startCol)
        }

        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                if (input[row][col] in symbols) {
                    // Check all adjacent positions including diagonals
                    for (i in -1..1) {
                        for (j in -1..1) {
                            val newRow = row + i
                            val newCol = col + j
                            if (newRow !in 0 until numRows || newCol !in 0 until numCols) continue

                            extractNumber(newRow, newCol)?.let { (number, startPos) ->
                                if (startPos !in countedNumbers) {
                                    sumOfParts += number.toInt()
                                    countedNumbers.add(startPos)
                                }
                            }
                        }
                    }
                }
            }
        }
        println(sumOfParts)
        return sumOfParts
    }

    fun part2(input: List<String>): Int {
        var totalGearRatio = 0
        val numRows = input.size
        val numCols = input[0].length

        // Function to extract a number at a given position
        fun extractNumber(row: Int, col: Int): Pair<String, Pair<Int, Int>>? {
            if (row !in 0 until numRows || col !in 0 until numCols || !input[row][col].isDigit()) {
                return null
            }

            var number = input[row][col].toString()
            var startRow = row
            var startCol = col

            // Check left for multi-digit numbers
            while (startCol - 1 >= 0 && input[row][startCol - 1].isDigit()) {
                startCol--
                number = input[row][startCol] + number
            }

            // Check right for multi-digit numbers
            var endCol = col + 1
            while (endCol < numCols && input[row][endCol].isDigit()) {
                number += input[row][endCol]
                endCol++
            }

            return number to (startRow to startCol)
        }

        for (row in 0 until numRows) {
            for (col in 0 until numCols) {
                if (input[row][col] == '*') {
                    val partNumbers = mutableSetOf<Int>()

                    // Check all adjacent positions including diagonals
                    for (i in -1..1) {
                        for (j in -1..1) {
                            // Skip the current symbol position
                            if (i == 0 && j == 0) continue

                            val newRow = row + i
                            val newCol = col + j
                            extractNumber(newRow, newCol)?.let { (number, startPos) ->
                                // Add only unique part numbers
                                partNumbers.add(number.toInt())
                            }
                        }
                    }

                    // Calculate gear ratio if exactly two unique part numbers are found
                    if (partNumbers.size == 2) {
                        val gearRatio = partNumbers.reduce { acc, num -> acc * num }
                        totalGearRatio += gearRatio
                    }
                }
            }
        }
        println(totalGearRatio)
        return totalGearRatio
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part2(input).println()
}
