package br.com.edu.observability.domain.errors

sealed class BusinessError(
    val message: String
) {

    data object UnexpectedError: BusinessError("Um erro inesperado ocorreu")
    data object NotFoundError: BusinessError("Não encontrado")

}