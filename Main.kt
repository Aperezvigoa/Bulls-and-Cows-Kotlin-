package bullscows

class Game (playerOne: Player, maximumAttemps: Int) {
    private var secretNumber = ""
    private val player = playerOne
    private val maxAttemps = maximumAttemps
    private var cows: Int = 0
    private var bulls: Int = 0
    private var codeLength = 4
        set(value) {
            if (value < 1 || value > 36)
                println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.")
            else
                field = value
        }

    private fun possibleSymbols(): Int {
        println("Input the number of possible symbols in the code:")
        return readln().toInt()
    }

    // Generate a list with numeric and letter characters with only the number of possible symbols
    private fun generateSymbolList(): Set<Char> {
        val availableSymbols: List<Char> = ('0'..'9') + ('a'..'z')
        val randomSymbols: MutableSet<Char> = mutableSetOf()
        val symbols = possibleSymbols()

        while (randomSymbols.size < symbols) {
            randomSymbols.add(availableSymbols.random())
        }
        return randomSymbols.toSet()
    }

    // Mix the list into a secret code
    private fun generateCode() {
        val randomSymbols = generateSymbolList()
        secretNumber = randomSymbols.shuffled().take(codeLength).joinToString("")
    }

    // Select the length of the code
    private fun selectingLength() {
        println("Please, enter the secret code's length: ")
        codeLength = readln().toInt()
    }

    // Check bulls into the user attemp
    private fun checkBulls(attemp: String): Int {
        var count: Int = 0
        for (i in 0 until codeLength) {
            if (attemp[i] == secretNumber[i])
                count++
        }
        return count
    }

    // Check cows into the user attemp
    private fun checkCows(attemp: String): Int {
        val userAttempList: List<Char> = attemp.toList()
        val secretWordList: List<Char> = secretNumber.toList()
        var count: Int = 0
        for (i in 0 until codeLength) {
            if (secretWordList.contains(userAttempList[i]))
                count++
        }
        return count
    }

    // Calculate the bulls and cows into the user code
    private fun evaluateAttemp(attemp: String) {
        bulls = checkBulls(attemp)
        cows = checkCows(attemp) - bulls
    }

    // return the feedback
    private fun grade() {
        println(
            when {
                cows == 0 && bulls == 0 -> "Grade: None. The secret code is $secretNumber."
                bulls == codeLength -> "Grade: $bulls bulls!\nCongratulations! You guessed the secret code."
                bulls == 0 -> "Grade: $cows cow(s)."
                cows == 0 -> "Grade: $bulls bull(s)."
                else -> "Grade: $bulls bull(s) and $cows cow(s). The secret code is $secretNumber."
            }
        )
    }

    fun play() {
        selectingLength()
        generateCode()
        println("Okay, let's start a game!")
        var attemps = 1
        while (attemps <= maxAttemps) {
            println("Turn ${attemps}:")
            evaluateAttemp(player.requestAttemp())
            grade()
            if (bulls == codeLength)
                break
            else if (attemps == maxAttemps) {
                println("You dont have more attemps...")
                break
            }
            attemps++
        }
    }
}

class Player {
    var name = "Player"
        set(value) {
            field = if (value.isBlank()) "Player" else value
        }
    private val _attemps: MutableList<String> = mutableListOf()
    val attemps: List<String> get() = _attemps

    fun requestAttemp(): String {
        val input = readln()
        addAttemp(input)
        return input
    }

    fun requestName() {
        println("Please, Write your name:")
        name = readln()
    }

    fun addAttemp(attemp: String) {
        _attemps.add(attemp)
    }
}

fun main() {
    val jugador = Player()
    val juego = Game(jugador, 300)
    juego.play()
}
