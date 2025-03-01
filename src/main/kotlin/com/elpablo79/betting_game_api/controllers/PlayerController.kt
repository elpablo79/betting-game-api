package com.elpablo79.betting_game_api.controllers

import com.elpablo79.betting_game_api.entities.Player
import com.elpablo79.betting_game_api.repositories.PlayerRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/players")
class PlayerController(private val playerRepository: PlayerRepository) {

    @PostMapping("/register")
    fun register(@RequestBody player: Player): Player {
        if (playerRepository.findByUsername(player.username) != null) {
            throw Exception("Username already exists")
        }
        return playerRepository.save(player)
    }

    @GetMapping("/{username}")
    fun getPlayer(@PathVariable username: String): Player {
        return playerRepository.findByUsername(username) ?: throw Exception("Player not found")
    }
}