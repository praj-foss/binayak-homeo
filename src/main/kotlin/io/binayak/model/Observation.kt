/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

/**
 * Purpose: Model for observations from a patient.
 */
class Observation(
        repertory: String,
        category: String,
        rubric: String
) {
    val repertoryProperty = SimpleStringProperty(repertory)
    var repertory by repertoryProperty

    val categoryProperty = SimpleStringProperty(category)
    var category by categoryProperty

    val rubricProperty = SimpleStringProperty(rubric)
    var rubric by rubricProperty
}