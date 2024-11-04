package br.com.edu.observability.application.usecases

import arrow.core.Either
import arrow.core.getOrElse
import br.com.edu.observability.domain.Logger
import br.com.edu.observability.domain.errors.BusinessError
import br.com.edu.observability.domain.model.Pokemon
import br.com.edu.observability.domain.repository.PokemonRepository
import br.com.edu.observability.infra.gateway.PokemonGateway
import org.springframework.stereotype.Service

@Service
class GetPokemonUseCase(
    val gateway: PokemonGateway,
    val repository: PokemonRepository
) {

    private val logger = Logger.logger(GetPokemonUseCase::class.java)

    fun execute(name: String): Either<BusinessError, Pokemon> {

        logger.info("Buscando o pokemon $name no DB.")

        repository.getByName(name)?.let {
            return Either.Right(it)
        }

        logger.info("Buscando o pokemon $name na API.")

        val pokemon = gateway.get(name).getOrElse { return Either.Left(it) }

        repository.save(pokemon)

        return Either.Right(pokemon)
    }

}