package io.binayak.util

import io.binayak.model.Case
import org.koin.dsl.module.module

/**
 * Purpose: Manages dependency injection modules
 */
object DependencyInjection {
    val injectionModules = listOf( module {
        factory { Case() }
    })
}