import java.util.Scanner

fun main() {
    var gameBoard = arrayOf(arrayOf(' ', '|', ' ', '|', ' '),
        arrayOf('-', '+', '-', '+', '-'),
        arrayOf(' ', '|', ' ', '|', ' '),
        arrayOf('-', '+', '-', '+', '-'),
        arrayOf(' ', '|', ' ', '|', ' ')
    )

    printGameBoard(gameBoard)

//    placePiece(gameBoard, )
}

fun printGameBoard(gameBoard: Array<Array<Char>>) {
    for (row in gameBoard) {
        for (c in row) {
            print(c)
        }
        println()
    }
}

fun placePiece(gameBoard: Array<Array<Char>>, pos: Int, user: String) {
    var token = ' '

    if(user == "player") {
        token = 'X'
    } else if (user == "cpu") {
        token = 'O'
    }

    val scanner = Scanner(System.`in`)
    println("Select a space to place your piece: ")
    when (scanner.nextInt()) {
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