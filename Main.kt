package bullscows

class CodeGenerator {
    var secretCode = ""
    var codeLength = 4
        set(value) {
            if (value < 1 || value > 36) {
                println("Invalid length.")
                field = 4
            }
            else
                field = value
        }

    fun updateCodeLength(length: String) {
        try {
            codeLength = length.toInt()
        } catch (e: NumberFormatException) {
            println("Error: \"$length\" isn't a valid number.")
        }
    }

    private fun generateSymbols(quantity: Int): Set<Char> {
        val totalSymbols: List<Char> = ('a'..'z') + ('0'..'9')
        val selectedSymbols: Set<Char> = totalSymbols.shuffled().take(quantity).toSet()
        return selectedSymbols
    }

    fun generateSecretCode(totalSymbols: Int) {
        val selectedSymbols = generateSymbols(totalSymbols)
        secretCode += selectedSymbols.shuffled().take(codeLength).joinToString("")
        println("The secret is prepared: ${"*".repeat(codeLength)} (0-9, a-z)")
    }
}

class AttempValidator {

    fun checkWin(userAttemp: String, secretCode: String) = userAttemp == secretCode

    fun checkCows(userAttemp: String, secretCode: String): Int {
        var count = 0
        for (i in 0 until secretCode.length) {
            if (secretCode.contains(userAttemp[i]))
                count++
        }
        return count
    }

    fun checkBulls(userAttemp: String, secretCode: String): Int {
        var count = 0
        for (i in 0 until secretCode.length) {
            if (userAttemp[i] == secretCode[i])
                count++
        }
        return count
    }
}

class Game {
    val validator = AttempValidator()
    val generator = CodeGenerator()
    private var currentAttemp = 1

    private fun grade(userAttemp: String, secretCode: String) {
        val bulls = validator.checkBulls(userAttemp, secretCode)
        val cows = validator.checkCows(userAttemp, secretCode) - bulls
        when {
            bulls == 0 && cows == 1 -> println("Grade: $cows cow")
            bulls == 1 && cows == 0 -> println("Grade:$bulls bull")
            bulls > 1 && cows == 0 -> println("Grade: $bulls bulls")
            bulls == 0 && cows > 1 -> println("Grade: $cows cows")
            bulls == 1 && cows == 1 -> println("Grade: $bulls bull and $cows cow")
            bulls > 1 && cows == 1 -> println("Grade: $bulls bulls and $cows cow")
            bulls == 1 && cows > 1 -> println("Grade: $bulls bull and $cows cows")
            bulls > 1 && cows > 1 -> println("Grade: $bulls bulls and $cows cows")
            else -> println("None")
        }
    }

    fun play() {
        println("Input the length of the secret code:")
        val lengthCodeInput = readln()
        generator.updateCodeLength(lengthCodeInput)

        println("Input the number of possible symbols in the code:")
        val possibleSymbols = readln().toInt()

        generator.generateSecretCode(possibleSymbols)
        println("Okay, let's start a game!")

        do {
            println("Turn $currentAttemp:")
            currentAttemp++
            val userAttemp = readln()
            grade(userAttemp, generator.secretCode)
            if (validator.checkWin(userAttemp, generator.secretCode)) {
                println("Congratulations! You guessed the secret code.")
                break
            }
        } while (true)
    }
}

fun main() {
    val partida = Game()
    partida.play()
}
