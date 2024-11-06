package br.com.edu.observability.infra.gateway

import arrow.core.Either
import br.com.edu.observability.domain.Logger
import br.com.edu.observability.domain.errors.BusinessError
import br.com.edu.observability.domain.model.Ability
import br.com.edu.observability.domain.model.Pokemon
import br.com.edu.observability.domain.model.Type
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
class PokemonGateway(
    private val mapper: ObjectMapper
) {

    private val logger: org.slf4j.Logger = Logger.logger(PokemonGateway::class.java)

    @Value("\${pokeapi.url}")
    lateinit var url: String

    fun get(name: String): Either<BusinessError, Pokemon> {

        val request = HttpRequest.newBuilder(URI(("$url/pokemon/$name")))
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .GET().build()

        val client = HttpClient.newHttpClient()
        
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        if (response.statusCode() == 404) return Either.Left(BusinessError.NotFoundError)

        if (response.statusCode() != 200) return Either.Left(BusinessError.UnexpectedError)

        val json = response.body()

        logger.debug("""
            Response
            body: $json
            Status: ${response.statusCode()}
            
            Request: ${request.uri()}
        """.trimIndent())

        val tree = mapper.readTree(json)

        val types = tree["types"].toList().map {
            Type(name = it["type"]["name"].asText())
        }

        val abilities = tree["abilities"].toList().map {
            Ability(name = it["ability"]["name"].asText())
        }

        val pokemon = Pokemon(
            id = tree["id"].asInt(),
            name = tree["name"].asText(),
            weight = tree["weight"].asInt(),
            height = tree["height"].asInt(),
            types = types,
            abilities = abilities
        )

        return Either.Right(pokemon)
    }

}
