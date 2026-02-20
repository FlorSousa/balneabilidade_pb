package br.ufpb.dcx.pac.models.shore.controller

import br.ufpb.dcx.pac.models.shore.dto.CreateShoreRequest
import br.ufpb.dcx.pac.models.shore.dto.ShoreResponse
import br.ufpb.dcx.pac.models.shore.service.ShoreService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v1/shores")
class ShoreController(val shoreService: ShoreService) {

    @GetMapping
    fun getAllShores(pageable: Pageable): ResponseEntity<Page<ShoreResponse>> {
        val shores = shoreService.getAllShores(pageable)
        return ResponseEntity.ok(shores)
    }

    @PostMapping
    fun createShore(@RequestBody @Valid request: CreateShoreRequest): ResponseEntity<Void> {
        shoreService.createShore(request)
        return ResponseEntity.ok().build()
    }
}