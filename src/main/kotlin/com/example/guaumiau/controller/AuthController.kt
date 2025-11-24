package com.example.guaumiau.controller

import com.example.guaumiau.model.LoginRequest
import com.example.guaumiau.model.LoginResponse
import com.example.guaumiau.model.User
import com.example.guaumiau.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val userRepository: UserRepository) {

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<Void> {
        if (userRepository.findByEmail(user.email) != null) {
            return ResponseEntity.badRequest().build() // Email ya existe
        }
        userRepository.save(user)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val user = userRepository.findByEmail(request.email)
        if (user != null && user.password == request.password) {
            // Simulación de Token (En prod usar JWT)
            val token = "token_falso_${user.id}"
            return ResponseEntity.ok(LoginResponse(token))
        }
        return ResponseEntity.status(401).build()
    }
}
