package br.ufpb.dcx.pac.models.measurement.repository

import br.ufpb.dcx.pac.models.measurement.model.Measurement
import org.springframework.data.jpa.repository.JpaRepository

interface MeasurementRepository : JpaRepository<Measurement, Long>{

}
