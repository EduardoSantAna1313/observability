package br.com.edu.observability.application.usecases

import br.com.edu.observability.domain.Logger
import br.com.edu.observability.domain.model.Pokemon
import br.com.edu.observability.domain.repository.PokemonRepository
import org.springframework.stereotype.Service

@Service
class ListPokemonUseCase(
    val repository: PokemonRepository
) {

    private val logger = Logger.logger(ListPokemonUseCase::class.java)

    fun execute(): List<Pokemon> {
        logger.info("Buscando todos os pokemons na base")
        return repository.findAll()
    }

}