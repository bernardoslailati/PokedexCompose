package com.dev.bernardoslailati.pokedex.commom.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val io = CoroutineScope(Dispatchers.IO)
val main = CoroutineScope(Dispatchers.Main)
val default = CoroutineScope(Dispatchers.Default)