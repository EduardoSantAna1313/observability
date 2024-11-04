package br.com.edu.observability.domain.repository

import br.com.edu.observability.domain.model.Pokemon

interface PokemonRepository {

    fun save(pokemon: Pokemon)

    fun getByName(name: String): Pokemon?

    fun findAll(): List<Pokemon>

}