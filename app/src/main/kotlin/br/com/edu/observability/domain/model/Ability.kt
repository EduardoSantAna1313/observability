package br.com.edu.observability.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name= "PokemonAbility")
class Ability(
    @Id
    val name: String
)
