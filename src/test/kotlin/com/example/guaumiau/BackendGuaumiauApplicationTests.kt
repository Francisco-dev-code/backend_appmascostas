package com.example.guaumiau

import com.example.guaumiau.model.Pet
import com.example.guaumiau.model.User
import com.example.guaumiau.repository.PetRepository
import com.example.guaumiau.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BackendGuaumiauApplicationTests {

	@Autowired
	lateinit var userRepository: UserRepository

	@Autowired
	lateinit var petRepository: PetRepository

	@Test
	fun contextLoads() {
		// Verifica que el contexto de Spring se carga correctamente
		assertNotNull(userRepository)
		assertNotNull(petRepository)
	}

	@Test
	fun testRepositoriesWork() {
		// Test: Los repositorios funcionan correctamente
		val initialCount = userRepository.count()
		assertNotNull(initialCount)
	}

	@Test
	fun testUserCanBeSaved() {
		// Test: Se puede guardar un usuario con mascotas (cascade)
		val pet1 = Pet(name = "Firulais", type = "Perro")
		val pet2 = Pet(name = "Michi", type = "Gato")
		
		val user = User(
			name = "Test User",
			email = "test.unique@example.com",
			password = "123456",
			phone = "555-1234",
			pets = mutableListOf(pet1, pet2)
		)

		val savedUser = userRepository.save(user)
		
		assertNotNull(savedUser.id)
		assertEquals(2, savedUser.pets.size)
		assertEquals("Firulais", savedUser.pets[0].name)
	}

	@Test
	fun testFindUserByEmail() {
		// Test: Se puede buscar usuario por email
		val user = User(
			name = "Maria Garcia",
			email = "maria.unique@example.com",
			password = "password123",
			phone = "555-5678",
			pets = mutableListOf()
		)
		userRepository.save(user)

		val found = userRepository.findByEmail("maria.unique@example.com")
		
		assertNotNull(found)
		assertEquals("Maria Garcia", found?.name)
	}

	@Test
	fun testPetRepositoryWorks() {
		// Test: Se pueden obtener todas las mascotas
		val allPets = petRepository.findAll()
		assertNotNull(allPets)
	}
}
