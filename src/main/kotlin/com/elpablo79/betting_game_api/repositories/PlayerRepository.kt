package com.elpablo79.betting_game_api.repositories

import com.elpablo79.betting_game_api.entities.Player
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRepository : JpaRepository<Player, Long> {
    fun findByUsername(username: String): Player?
}