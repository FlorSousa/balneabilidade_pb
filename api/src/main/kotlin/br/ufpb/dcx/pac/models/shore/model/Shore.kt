package br.ufpb.dcx.pac.models.shore.model

import br.ufpb.dcx.pac.models.measurement.model.Measurement
import br.ufpb.dcx.pac.models.shore.dto.ShoreResponse
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "praia")
class Shore(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(name = "nome_praia", length = 255)
    var name: String?,
    @Column(name = "cidade")
    var city: String?,
    @OneToMany(
        mappedBy = "shore",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var measurements: MutableList<Measurement> = mutableListOf()
) {

    fun toResponse(): ShoreResponse {
        return ShoreResponse(
            id = id, nome = name, cidade = city, medicoes = measurements.map { it.toResponse() })
    }

}