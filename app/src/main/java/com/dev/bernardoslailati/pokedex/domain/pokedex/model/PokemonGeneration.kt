package com.dev.bernardoslailati.pokedex.domain.pokedex.model

enum class PokemonGeneration(val from: Int, val to: Int, val isSyncGenerationKey: String) {

    FIRST(from = 1, to = 151, isSyncGenerationKey = "is_sync_pokemons_from_generation_1");

    /**
     * An int range of all specific generation`s pokemons ids (i.e. Generation 1 -> IntRange(1..151)).
     */
    fun rangeIds(): IntRange = from..to

}