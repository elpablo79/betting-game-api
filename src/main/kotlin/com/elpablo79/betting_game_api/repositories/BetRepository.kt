package com.elpablo79.betting_game_api.repositories

import com.elpablo79.betting_game_api.entities.Bet
import com.elpablo79.betting_game_api.entities.Player
import org.springframework.data.jpa.repository.JpaRepository

interface BetRepository : JpaRepository<Bet, Long>{
    fun findByPlayer(player: Player): List<Bet>
}