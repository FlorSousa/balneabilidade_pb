package br.ufpb.dcx.pac.models.measurement.controller

import br.ufpb.dcx.pac.models.measurement.dto.MeasurementResponse
import br.ufpb.dcx.pac.models.measurement.service.MeasurementService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/v1/measurements"], produces = ["application/json"])
class MeasurementController(
    val measurementService: MeasurementService
) {
    @GetMapping
    fun getAllMeasurements(pageable: Pageable): ResponseEntity<Page<MeasurementResponse>> {
        val response = measurementService.getAllMeasurements(pageable)
        return ResponseEntity.ok(response)
    }

}