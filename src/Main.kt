import java.util.Random
import java.util.Scanner

private var playerPositions = mutableListOf<Int>()
private var cpuPositions = mutableListOf<Int>()

fun main() {
    val gameBoard = arrayOf(
        arrayOf(' ', '|', ' ', '|', ' '),
        arrayOf('-', '+', '-', '+', '-'),
        arrayOf(' ', '|', ' ', '|', ' '),
        arrayOf('-', '+', '-', '+', '-'),
        arrayOf(' ', '|', ' ', '|', ' ')
    )

    printGameBoard(gameBoard)

    while (true) {
        placePiece(gameBoard, "player")
        var result = checkWinner(playerPositions, cpuPositions)
        if (result.isNotEmpty()) {
            println(result)
            break
        }
        placePiece(gameBoard, "cpu")
        result = checkWinner(playerPositions, cpuPositions)
        if (result.isNotEmpty()) {
            println(result)
            break
        }
        printGameBoard(gameBoard)
    }

    printGameBoard(gameBoard)
}

private fun printGameBoard(gameBoard: Array<Array<Char>>) {
    for (row in gameBoard) {
        for (c in row) {
            print(c)
        }
        println()
    }
}

private fun placePiece(gameBoard: Array<Array<Char>>, user: String) {
    var token = ' '

    if (user == "player") {
        token = 'X'
        val scanner = Scanner(System.`in`)
        println("Select a space to place your piece: ")
        var playerPos = scanner.nextInt()
        while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
            println("Invalid placement. Try again.")
            playerPos = scanner.nextInt()
        }
        chooseSpace(playerPos, gameBoard, token)
        playerPositions.add(playerPos)
    } else if (user == "cpu") {
        token = 'O'
        val rand = Random()
        var cpuPos = rand.nextInt(9) + 1
        while (cpuPositions.contains(cpuPos) || playerPositions.contains(cpuPos)) {
            cpuPos = rand.nextInt(9) + 1
        }
        chooseSpace(cpuPos, gameBoard, token)
        cpuPositions.add(cpuPos)
    }
}

private fun chooseSpace(space: Int, gameBoard: Array<Array<Char>>, token: Char) {
    when (space) {
        1 -> gameBoard[0][0] = token
        2 -> gameBoard[0][2] = token
        3 -> gameBoard[0][4] = token
        4 -> gameBoard[2][0] = token
        5 -> gameBoard[2][2] = token
        6 -> gameBoard[2][4] = token
        7 -> gameBoard[4][0] = token
        8 -> gameBoard[4][2] = token
        9 -> gameBoard[4][4] = token
    }
}

private fun checkWinner(playerPositions: List<Int>, cpuPositions: List<Int>): String {
    val row1 = listOf(1, 2, 3)
    val row2 = listOf(4, 5, 6)
    val row3 = listOf(7, 8, 9)
    val col1 = listOf(1, 4, 7)
    val col2 = listOf(2, 5, 8)
    val col3 = listOf(3, 6, 9)
    val cross1 = listOf(1, 5, 9)
    val cross2 = listOf(3, 5, 7)
    val winCombos = arrayListOf(row1, row2, row3, col1, col2, col3, cross1, cross2)

    for(combo in winCombos) {
        if (playerPositions.containsAll(combo)) {
            return "Congratulations! You won the game!"
        } else if (cpuPositions.containsAll(combo)) {
            return "The CPU won. Sorry :("
        }
    }
    if ((playerPositions.size + cpuPositions.size) == 9) {
        return "CAT!"
    }

    return ""
}
