package com.elpablo79.betting_game_api.repositories

import com.elpablo79.betting_game_api.entities.Player
import com.elpablo79.betting_game_api.entities.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByPlayer(player: Player): List<Transaction>
}