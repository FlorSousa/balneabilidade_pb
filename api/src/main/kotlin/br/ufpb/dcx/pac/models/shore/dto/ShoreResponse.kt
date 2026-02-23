package br.ufpb.dcx.pac.models.shore.dto

import br.ufpb.dcx.pac.models.measurement.dto.MeasurementResponse

data class ShoreResponse(
    val id: Long?,
    val nome: String?,
    val cidade: String?,
    val medicoes: List<MeasurementResponse> = emptyList()
)

