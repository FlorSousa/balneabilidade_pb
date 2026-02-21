package br.ufpb.dcx.pac.models.measurement.service

import br.ufpb.dcx.pac.models.measurement.dto.MeasurementResponse
import br.ufpb.dcx.pac.models.measurement.repository.MeasurementRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MeasurementService(
    val measurementRepository: MeasurementRepository
){
    fun getAllMeasurements(pageable: Pageable): Page<MeasurementResponse> {
        return measurementRepository.findAll(pageable).map { it.toResponse() }
    }
}
