package br.ufpb.dcx.pac.models.shore.repository

import br.ufpb.dcx.pac.models.shore.model.Shore
import org.springframework.data.jpa.repository.JpaRepository

interface ShoreRepository : JpaRepository<Shore, Long>