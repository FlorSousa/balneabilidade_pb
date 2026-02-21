package br.ufpb.dcx.pac.models.shore.dto

import br.ufpb.dcx.pac.models.shore.model.Shore
import jakarta.validation.constraints.NotBlank

data class CreateShoreRequest(
    @NotBlank(message = "O nome da praia é obrigatório")
    val nome: String?,
    @NotBlank(message = "A cidade da praia é obrigatória")
    val cidade: String?
) {
    fun toEntity(): Shore {
        return Shore(
            null,
            name = this.nome,
            city = this.cidade,
        )
    }
}
