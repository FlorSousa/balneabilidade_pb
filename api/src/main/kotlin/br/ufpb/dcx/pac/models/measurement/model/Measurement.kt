package br.ufpb.dcx.pac.models.measurement.model

import br.ufpb.dcx.pac.models.measurement.dto.MeasurementResponse
import br.ufpb.dcx.pac.models.shore.model.Shore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "medicao")
class Measurement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,

    @Column(name = "data_medicao")
    var readingDate: LocalDateTime?,

    @Column(name = "qualidade")
    var quality: Boolean?,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    var shore: Shore
) {
    fun toResponse(): MeasurementResponse {
        return MeasurementResponse(id, shore.id, readingDate, quality, createdAt)
    }

}