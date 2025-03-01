package com.elpablo79.betting_game_api.entities

import jakarta.persistence.*

@Entity
data class Bet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    val player: Player,
    val betAmount: Int,
    val betNumber: Int,
    val generatedNumber: Int,
    val winnings: Int
)