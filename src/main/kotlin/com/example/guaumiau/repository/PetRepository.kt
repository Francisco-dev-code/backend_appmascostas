package com.example.guaumiau.repository

import com.example.guaumiau.model.Pet
import org.springframework.data.jpa.repository.JpaRepository

interface PetRepository : JpaRepository<Pet, Long>
