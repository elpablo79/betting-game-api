package com.elpablo79.betting_game_api.entities

import jakarta.persistence.*

@Entity
data class Player(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val surname: String,
    @Column(unique = true)
    val username: String,
    var walletBalance: Int = 1000
)