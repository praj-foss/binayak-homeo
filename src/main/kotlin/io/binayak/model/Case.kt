/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.model

import java.time.LocalDate

/**
 * Purpose: Model for patient cases
 */
data class Case(
        var date: LocalDate? = null,
        var name: String? = null,
        var age: Int?   = null,
        var sex: Sex?  = null,

        // TODO: Check and use immutable collections
        var observations: List<Observation> = emptyList(),
        var results: List<Result> = emptyList()
)