package com.example.guaumiau.controller

import com.example.guaumiau.model.Pet
import com.example.guaumiau.repository.PetRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pets")
class PetController(private val petRepository: PetRepository) {

    @GetMapping
    fun getPets(): List<Pet> {
        return petRepository.findAll()
    }

    @PostMapping
    fun addPet(@RequestBody pet: Pet): Pet {
        return petRepository.save(pet)
    }

    @PutMapping("/{id}")
    fun updatePet(@PathVariable id: Long, @RequestBody petDetails: Pet): ResponseEntity<Pet> {
        return petRepository.findById(id).map { existingPet ->
            val updatedPet = existingPet.copy(
                name = petDetails.name,
                type = petDetails.type
            )
            ResponseEntity.ok(petRepository.save(updatedPet))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deletePet(@PathVariable id: Long): ResponseEntity<Void> {
        return if (petRepository.existsById(id)) {
            petRepository.deleteById(id)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
