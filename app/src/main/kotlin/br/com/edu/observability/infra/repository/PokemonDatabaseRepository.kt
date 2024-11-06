package br.com.edu.observability.infra.repository

import br.com.edu.observability.domain.model.Pokemon
import br.com.edu.observability.domain.repository.PokemonRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonDatabaseRepository: PokemonRepository, JpaRepository<Pokemon, String>
