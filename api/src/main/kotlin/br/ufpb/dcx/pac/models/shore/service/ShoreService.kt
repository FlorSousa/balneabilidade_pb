package br.ufpb.dcx.pac.models.shore.service

import br.ufpb.dcx.pac.exceptionhandler.exceptions.EntityNotFoundException
import br.ufpb.dcx.pac.models.shore.dto.CreateShoreRequest
import br.ufpb.dcx.pac.models.shore.dto.ShoreResponse
import br.ufpb.dcx.pac.models.shore.repository.ShoreRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ShoreService(
    val shoreRepository: ShoreRepository
) {
    fun getAllShores(pageable: Pageable): Page<ShoreResponse> {
        val shores = shoreRepository.findAll(pageable)
        return shores.map { it.toResponse() }
    }

    fun getShoreById(id: Long): ShoreResponse {
        val shore = shoreRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Shore not found with id $id") }
        return shore.toResponse()
    }

    fun createShore(request: CreateShoreRequest): ShoreResponse {
        val shore = request.toEntity()

        return shoreRepository.save(shore).toResponse()
    }
}
