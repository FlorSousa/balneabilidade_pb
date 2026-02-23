package br.ufpb.dcx.pac.models.measurement.controller

import br.ufpb.dcx.pac.models.measurement.dto.MeasureCreateRequest
import br.ufpb.dcx.pac.models.measurement.dto.MeasurementResponse
import br.ufpb.dcx.pac.models.measurement.service.MeasurementService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping(value = ["/v1/measurements"], produces = ["application/json"])
class MeasurementController(
    val measurementService: MeasurementService
) {
    @GetMapping
    fun getAllMeasurements(pageable: Pageable): ResponseEntity<Page<MeasurementResponse>> {
        val response = measurementService.getAllMeasurements(pageable)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getMeasurementById(@PathVariable id: Long): ResponseEntity<MeasurementResponse> {
        val response = measurementService.getMeasurementById(id)
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun createMeasurement(request: MeasureCreateRequest): ResponseEntity<MeasurementResponse> {
        val response = measurementService.createMeasurement(request)
        return ResponseEntity.created(URI.create("/api/v1/measurements/${response.id}")).body(response)
    }

}