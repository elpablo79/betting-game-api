package com.elpablo79.betting_game_api.services

import com.elpablo79.betting_game_api.entities.Bet
import com.elpablo79.betting_game_api.entities.Player
import com.elpablo79.betting_game_api.entities.Transaction
import com.elpablo79.betting_game_api.repositories.BetRepository
import com.elpablo79.betting_game_api.repositories.PlayerRepository
import com.elpablo79.betting_game_api.repositories.TransactionRepository
import org.springframework.stereotype.Service
import kotlin.math.absoluteValue
import kotlin.random.Random

@Service
class GameService(
    private val playerRepository: PlayerRepository,
    private val betRepository: BetRepository,
    private val transactionRepository: TransactionRepository
) {
    fun placeBet(username: String, betAmount: Int, betNumber: Int): Bet {
        val player = playerRepository.findByUsername(username)
            ?: throw IllegalArgumentException("Player not found")

        if (player.walletBalance < betAmount) {
            throw IllegalArgumentException("Insufficient balance")
        }

        player.walletBalance -= betAmount
        playerRepository.save(player)

        val bet = Bet(
            id = 0, // Assuming ID is auto-generated
            player = player,
            betAmount = betAmount,
            betNumber = betNumber,
            generatedNumber = (1..10).random(), // Generating a random number
            winnings = 0 // Assuming winnings are calculated later
        )

        betRepository.save(bet)
        transactionRepository.save(Transaction(player = player, amount = betAmount, type = "BET", description = "Bet placed"))

        return bet
    }

    private fun calculateWinnings(betNumber: Int, generatedNumber: Int, betAmount: Int): Int {
        return when {
            betNumber == generatedNumber -> betAmount * 10
            (betNumber - generatedNumber).absoluteValue == 1 -> betAmount * 5
            (betNumber - generatedNumber).absoluteValue == 2 -> betAmount / 2
            else -> 0
        }
    }

    fun getTransactions(username: String): List<Transaction> {
        val player = playerRepository.findByUsername(username) ?: throw Exception("Player not found")
        return transactionRepository.findByPlayer(player)
    }

    fun getLeaderboard(): List<Player> {
        return playerRepository.findAll().sortedByDescending { player ->
            betRepository.findByPlayer(player).sumOf { it.winnings }
        }
    }
}