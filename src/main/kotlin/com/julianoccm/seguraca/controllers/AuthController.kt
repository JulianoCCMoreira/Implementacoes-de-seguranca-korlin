package com.julianoccm.seguraca.controllers

import com.julianoccm.seguraca.dtos.LoginDTO
import com.julianoccm.seguraca.dtos.Message
import com.julianoccm.seguraca.dtos.RegisterDTO
import com.julianoccm.seguraca.models.User
import com.julianoccm.seguraca.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("auth")
class AuthController(private val userService: UserService) {
    val jwtSecret: String =  System.getenv("JWT_SECRET")

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<Any> {
        val user = User()
        user.name = body.name
        user.email = body.email
        user.password = body.password

        return ResponseEntity.ok().body(this.userService.save(user))
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any> {
        val user = userService.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body(Message("Usuario não encontrado"))

        if(!user.comparePasswords(body.password)) {
            return ResponseEntity.badRequest().body(Message("Senha invalida"))
        }

        val userId = user.Id.toString()

        val tokenJwt = Jwts.builder()
            .setIssuer(userId)
            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 1000)) //1 dia de duração do Token
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()

        val cookie = Cookie("jwt", tokenJwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Realizado o login com sucesso"))
    }

    @GetMapping("user")
    fun getUser(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
           try {
               if(jwt == null) {
                   return ResponseEntity.status(401).body(Message("Nao autenticado"))
               }

               val body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).body

               return ResponseEntity.ok(this.userService.getById(body.issuer.toInt()))
           } catch (exception: Exception) {
               println(exception.message)
               return ResponseEntity.status(401).body(Message("Nao autorizado"))
           }
    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Logout feito com sucesso"))
    }
}