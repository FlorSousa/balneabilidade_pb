package br.ufpb.dcx.pac.models.measurement.service

import br.ufpb.dcx.pac.exceptionhandler.exceptions.EntityNotFoundException
import br.ufpb.dcx.pac.models.measurement.dto.MeasureCreateRequest
import br.ufpb.dcx.pac.models.measurement.dto.MeasurementResponse
import br.ufpb.dcx.pac.models.measurement.repository.MeasurementRepository
import br.ufpb.dcx.pac.models.shore.repository.ShoreRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class MeasurementService(
    val measurementRepository: MeasurementRepository,
    val shoreRepository: ShoreRepository
) {
    fun getAllMeasurements(pageable: Pageable): Page<MeasurementResponse> {
        return measurementRepository.findAll(pageable).map { it.toResponse() }
    }

    fun getMeasurementById(id: Long): MeasurementResponse {
        val measurement = measurementRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Measurement not found with id: $id") }
        return measurement.toResponse()
    }

    fun createMeasurement(request: MeasureCreateRequest): MeasurementResponse {
        val shore = shoreRepository.findShoreById(request.shoreId)
            .orElseThrow { EntityNotFoundException("Shore not found with id: ${request.shoreId}") }

        val measurement = request.toEntity()
        measurement.shore = shore

        return measurementRepository.save(measurement).toResponse()
    }
}
