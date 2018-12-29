/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.model

import javafx.beans.property.*
import javafx.collections.ObservableList
import java.time.LocalDate
import tornadofx.*

/**
 * Purpose: Model for patient cases.
 */
class Case(
        date: LocalDate,
        name: String,
        age: Int,
        sex: Sex,
        observations: ObservableList<Observation>,
        results: ObservableList<Result>
) {
    val dateProperty = SimpleObjectProperty<LocalDate>(date)
    var date by dateProperty

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

    val ageProperty = SimpleIntegerProperty(age)
    var age by ageProperty

    val sexProperty = SimpleObjectProperty<Sex>(sex)
    var sex by sexProperty

    val observationsProperty = SimpleListProperty<Observation>(observations)
    var observations by observationsProperty

    val resultsProperty = SimpleListProperty<Result>(results)
    var results by resultsProperty
}