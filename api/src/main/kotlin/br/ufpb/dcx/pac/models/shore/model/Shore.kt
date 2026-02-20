package br.ufpb.dcx.pac.models.shore.model

import br.ufpb.dcx.pac.models.shore.dto.ShoreResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "praia")
class Shore (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "nome_praia", length = 255)
    val name: String?,
    @Column(name = "cidade")
    val city: String?
) {
    constructor(name: String?, city: String?) : this(null, name, city)

    fun toResponse(): ShoreResponse {
        return ShoreResponse(id, name, city)
    }

}