package br.ufpb.dcx.pac.models.measurement.dto

import br.ufpb.dcx.pac.models.measurement.model.Measurement
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class MeasureCreateRequest(
    @NotNull(message = "O campo shoreId é obrigatório.")
    var shoreId: Long,
    @NotNull(message = "O campo readingDate é obrigatório.")
    var readingDate: LocalDateTime,
    @NotNull(message = "O campo quality é obrigatório.")
    var quality: Boolean
) {
    fun toEntity(): Measurement {
        return Measurement(
            id = null,
            shore = null, // O valor da praia será definido no serviço
            readingDate = this.readingDate,
            isProperForBathing = this.quality,
            createdAt = LocalDateTime.now()
        )
    }
}
