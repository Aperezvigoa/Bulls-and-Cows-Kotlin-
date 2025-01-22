package bullscows

import kotlin.random.Random
import kotlin.random.nextInt

class Game (_player: Player, _maxAttemps: Int) {
    var secretNumber = ""
    val player = _player
    val attemps = player.attemps.size
    val maxAttemps = _maxAttemps
    var cows: Int = 0
    var bulls: Int = 0
    private var _codeLength = 4
        set(value) {
            if (value < 1 || value > 10)
                println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.")
            else
                field = value
        }

    fun generateNumber() {
        val tempList: MutableList<Int> = mutableListOf()
        while (tempList.size < _codeLength) {
            var randNum: Int
            do {
                randNum = Random.nextInt(0..9)
                if (!tempList.contains(randNum))
                    break
            } while (true)
            tempList.add(randNum)
        }
        secretNumber = tempList.joinToString("")
    }

    fun selectingLength() {
        println("Select the length of the secret number: ")
        _codeLength = readln().toInt()
    }

    fun requestAttemp(): String {
        val input = readln()
        player.addAttemp(input)
        return input
    }

    private fun checkBulls(attemp: String): Int {
        var count: Int = 0
        for (i in 0 until 4) {
            if (attemp[i] == secretNumber[i])
                count++
        }
        return count
    }

    private fun checkCows(attemp: String): Int {
        val userAttempList: List<Char> = attemp.toList()
        val secretWordList: List<Char> = secretNumber.toList()
        var count: Int = 0
        for (i in 0 until 4) {
            if (secretWordList.contains(userAttempList[i]))
                count++
        }
        return count
    }

    fun checkAttemp(attemp: String) {
        bulls = checkBulls(attemp)
        cows = checkCows(attemp) - bulls
    }

    fun grade() {
        println(
            when {
                cows == 0 && bulls == 0 -> "Grade: None. The secret code is $secretNumber."
                bulls == 4 -> "Grade: Congrats! The secret code is $secretNumber."
                bulls == 0 -> "Grade: $cows cow(s). The secret code is $secretNumber."
                cows == 0 -> "Grade: $bulls bull(s). The secret code is $secretNumber."
                else -> "Grade: $bulls bull(s) and $cows cow(s). The secret code is $secretNumber."
            }
        )
    }
}

class Player {
    var name = "Player"
        set(value) {
            field = if (value.isBlank()) "Player" else value
        }
    private val _attemps: MutableList<String> = mutableListOf()
    val attemps: List<String> get() = _attemps

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
    val juego = Game(jugador, 1)

    juego.selectingLength()
    juego.generateNumber()
    println("The random secret number is ${juego.secretNumber}.")
}
