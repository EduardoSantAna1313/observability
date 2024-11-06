package br.com.edu.observability.domain.gateway

import arrow.core.Either
import br.com.edu.observability.domain.errors.BusinessError
import br.com.edu.observability.domain.model.Pokemon

fun interface PokemonGateway {

    fun get(name: String): Either<BusinessError, Pokemon>

}