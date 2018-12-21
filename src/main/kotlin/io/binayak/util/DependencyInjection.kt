/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.util

import io.binayak.service.SavedCasesService
import org.koin.dsl.module.module

/**
 * Purpose: Manages dependency injection modules
 */
object DependencyInjection {
    val injectionModules = listOf( module {
        single { SavedCasesService() }
    })
}