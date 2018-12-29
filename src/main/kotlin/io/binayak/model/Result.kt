/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

/**
 * Purpose: Model for results corresponding to observations.
 */
class Result(
        medicine: String,
        marks: Int
) {
    val medicineProperty = SimpleStringProperty(medicine)
    var medicine by medicineProperty

    val marksProperty = SimpleIntegerProperty(marks)
    var marks by marksProperty
}