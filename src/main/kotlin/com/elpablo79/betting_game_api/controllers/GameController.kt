package com.elpablo79.betting_game_api.controllers

import com.elpablo79.betting_game_api.entities.Bet
import com.elpablo79.betting_game_api.entities.Player
import com.elpablo79.betting_game_api.entities.Transaction
import com.elpablo79.betting_game_api.services.GameService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/game")
class GameController(private val gameService: GameService) {

    @PostMapping("/bet")
    fun placeBet(@RequestParam username: String, @RequestParam betAmount: Int, @RequestParam betNumber: Int): Bet {
        return gameService.placeBet(username, betAmount, betNumber)
    }

    @GetMapping("/transactions")
    fun getTransactions(@RequestParam username: String): List<Transaction> {
        return gameService.getTransactions(username)
    }

    @GetMapping("/leaderboard")
    fun getLeaderboard(): List<Player> {
        return gameService.getLeaderboard()
    }
}