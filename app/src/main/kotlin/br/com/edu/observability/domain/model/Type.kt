package br.com.edu.observability.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name= "PokemonType")
class Type(

    @Id
    val name: String
)