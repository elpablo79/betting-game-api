package com.elpablo79.betting_game_api.entities

import jakarta.persistence.*

@Entity
data class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    val player: Player,
    val amount: Int,  // Negative for bets, positive for winnings
    val type: String,  // "BET" or "WIN"
    val description: String
)