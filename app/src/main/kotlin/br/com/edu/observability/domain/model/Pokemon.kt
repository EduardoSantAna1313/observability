package br.com.edu.observability.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "Pokemon")
class Pokemon(

    @Id
    val id: Int,

    val name: String,

    val weight: Int,

    val height: Int,

    @ManyToMany(cascade = [CascadeType.ALL])
    val types: List<Type>,

    @ManyToMany(cascade = [CascadeType.ALL])
    val abilities: List<Ability>
)
