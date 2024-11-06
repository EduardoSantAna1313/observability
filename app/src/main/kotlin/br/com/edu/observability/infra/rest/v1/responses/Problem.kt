package br.com.edu.observability.infra.rest.v1.responses

data class Problem(
    val message: String,
    val status: Int
)