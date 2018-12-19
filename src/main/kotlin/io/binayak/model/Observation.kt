/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.model

/**
 * Purpose: Model for observations from a patient
 */
data class Observation(
        val repertory: String,
        val category: String,
        val rubric: String
)