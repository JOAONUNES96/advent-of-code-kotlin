fun main() {
    fun part1(input: List<String>): Int {
        var totalCards = 0
        val cardsQueue = ArrayDeque<Pair<Int, String>>() // Queue to hold index and card data

        // Initialize the queue with the original set of cards and their indices
        input.forEachIndexed { index, card ->
            cardsQueue.addLast(index to card)
        }

        while (cardsQueue.isNotEmpty()) {
            val (currentIndex, currentCard) = cardsQueue.removeFirst()
            totalCards++  // Count every card processed, including copies

            val parts = currentCard.split(": ")
            val myNumbers = parts[1].split(" | ")[0].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val gameNumbers = parts[1].split(" | ")[1].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

            var matches = 0
            myNumbers.forEach { number ->
                if (number in gameNumbers) {
                    matches++
                }
            }

            // Add copies of the subsequent cards for each match
            for (i in 1..matches) {
                val nextCardIndex = currentIndex + i
                if (nextCardIndex < input.size) {
                    cardsQueue.addLast(nextCardIndex to input[nextCardIndex])
                }
            }
        }
        println(totalCards)
        return totalCards
    }

    fun part2(input: List<String>): Int {
        var totalCards = 0
        val cardsQueue = ArrayDeque<Pair<Int, String>>() // Queue to hold index and card data

        // Initialize the queue with the original set of cards and their indices
        input.forEachIndexed { index, card ->
            cardsQueue.addLast(index to card)
        }

        while (cardsQueue.isNotEmpty()) {
            val (currentIndex, currentCard) = cardsQueue.removeFirst()
            totalCards++  // Count every card processed, including copies

            val parts = currentCard.split(": ")
            val myNumbers = parts[1].split(" | ")[0].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val gameNumbers = parts[1].split(" | ")[1].split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

            var matches = 0
            myNumbers.forEach { number ->
                if (number in gameNumbers) {
                    matches++
                }
            }

            // Add copies of the subsequent cards for each match
            for (i in 1..matches) {
                val nextCardIndex = currentIndex + i
                if (nextCardIndex < input.size) {
                    cardsQueue.addLast(nextCardIndex to input[nextCardIndex])
                }
            }
        }
        println(totalCards)
        return totalCards
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 30)

    val input = readInput("Day04")
    part2(input).println()
}
