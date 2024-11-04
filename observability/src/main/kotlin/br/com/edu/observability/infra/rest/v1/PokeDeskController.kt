package br.com.edu.observability.infra.rest.v1

import arrow.core.getOrElse
import br.com.edu.observability.application.usecases.GetPokemonUseCase
import br.com.edu.observability.application.usecases.ListPokemonUseCase
import br.com.edu.observability.domain.errors.BusinessError
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/pokemon")
class PokeDeskController(
    val getPokemonUseCase: GetPokemonUseCase,
    val listPokemonUseCase: ListPokemonUseCase
) {

    @GetMapping("/{name}")
    fun get(@PathVariable name: String): ResponseEntity<*> {
        val pokemon = getPokemonUseCase.execute(name).getOrElse {
            return handleErrorResponse(it)
        }
        return ResponseEntity.ok(pokemon)
    }

    @GetMapping
    fun list(): ResponseEntity<*> {
        val pokemon = listPokemonUseCase.execute()
        return ResponseEntity.ok(pokemon)
    }
}

fun handleErrorResponse(error: BusinessError): ResponseEntity<*> {
    val status = when(error) {
        BusinessError.UnexpectedError -> 500
        BusinessError.NotFoundError -> 404
    }
    return ResponseEntity.status(status).body(Problem(error.message))

}

data class Problem(
    val message: String
)