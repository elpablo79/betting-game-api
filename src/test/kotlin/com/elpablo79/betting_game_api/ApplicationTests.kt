package com.elpablo79.betting_game_api

import com.elpablo79.betting_game_api.entities.Bet
import com.elpablo79.betting_game_api.entities.Player
import com.elpablo79.betting_game_api.entities.Transaction
import com.elpablo79.betting_game_api.repositories.BetRepository
import com.elpablo79.betting_game_api.repositories.PlayerRepository
import com.elpablo79.betting_game_api.repositories.TransactionRepository
import com.elpablo79.betting_game_api.services.GameService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationTests {
    private val playerRepository: PlayerRepository = mockk(relaxed = true)
    private val betRepository: BetRepository = mockk(relaxed = true)
    private val transactionRepository: TransactionRepository = mockk(relaxed = true)
    private val gameService = GameService(playerRepository, betRepository, transactionRepository)

    @Test
    fun `should deduct balance and store bet`() {
        val player = Player(id = 1, name = "Mark", surname = "Donovan", username = "markdonovan", walletBalance = 1000)
        every { playerRepository.findByUsername("markdonovan") } returns player
        every { playerRepository.save(any()) } answers { firstArg() }
        every { betRepository.save(any()) } answers { firstArg() as Bet }
        every { transactionRepository.save(any()) } answers { firstArg() as Transaction }

        val placedBet = gameService.placeBet("markdonovan", 100, 5)

        assertEquals(100, placedBet.betAmount, "Bet amount should be 100")
        assertEquals(900, player.walletBalance, "Player's wallet balance should be 900 after placing a bet")

        verify { playerRepository.save(player) }
        verify { betRepository.save(any()) }
        verify { transactionRepository.save(any()) }
    }
}