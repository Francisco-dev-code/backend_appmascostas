package com.example.guaumiau.model

import jakarta.persistence.*

@Entity
@Table(name = "pets")
data class Pet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String = "",
    val type: String = ""
)
