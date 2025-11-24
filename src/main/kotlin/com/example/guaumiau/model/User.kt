package com.example.guaumiau.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // Nullable para que la BD genere el ID

    val name: String = "",
   
    @Column(unique = true)
    val email: String = "",
   
    val password: String = "", // En producción, esto debe ir encriptado
    val phone: String = "",

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val pets: MutableList<Pet> = mutableListOf()
)
