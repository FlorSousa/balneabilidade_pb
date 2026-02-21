package br.ufpb.dcx.pac.models.measurement.dto

import java.time.LocalDateTime

data class MeasurementResponse(
    val id: Long?,
    val shoreId: Long?,
    val readingDate: LocalDateTime?,
    val quality: Boolean?,
    val createdAt: LocalDateTime?,
) {

}
