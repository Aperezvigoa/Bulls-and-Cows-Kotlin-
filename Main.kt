package bullscows

import kotlin.random.Random
import kotlin.random.nextInt

class Game (_player: Player, _maxAttemps: Int) {
    var secretNumber = "9305"
    val player = _player
    val attemps = player.attemps.size
    val maxAttemps = _maxAttemps
    var cows: Int = 0
    var bulls: Int = 0

    fun generateNumber() {
        while (secretNumber.length < 4) {
            val randNum = Random.nextInt(0..9)
            secretNumber += randNum
        }
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
                //bulls == 4 -> "Grade: Congrats! The secret code is $secretNumber."
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
    juego.checkAttemp(juego.requestAttemp())
    juego.grade()
}
