package br.ufpb.dcx.pac.models.shore.service

import br.ufpb.dcx.pac.models.shore.dto.CreateShoreRequest
import br.ufpb.dcx.pac.models.shore.dto.ShoreResponse
import br.ufpb.dcx.pac.models.shore.repository.ShoreRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ShoreService(
    val shoreRepository: ShoreRepository
) {
    fun getAllShores(pageable: Pageable): Page<ShoreResponse> {
        val shores = shoreRepository.findAll(pageable)
        return shores.map { it.toResponse() }
    }

    fun createShore(request: CreateShoreRequest) {
        val shore = request.toEntity()
        shoreRepository.save(shore)
    }
}
