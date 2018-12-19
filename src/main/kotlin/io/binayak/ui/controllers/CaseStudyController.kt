/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.ui.controllers

import io.binayak.model.Case
import io.binayak.model.Observation
import io.binayak.model.Result
import io.binayak.model.Sex
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.*
import javafx.util.Callback
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import java.time.LocalDate

/**
 * Purpose: Controller for Case study view
 */
class CaseStudyController : KoinComponent {
    // Primary buttons
    lateinit var btnOpen: Button
    lateinit var btnSave: Button

    // Case details
    lateinit var fieldDate: DatePicker
    lateinit var fieldName: TextField
    lateinit var fieldAge: TextField
    lateinit var fieldSex: ComboBox<Sex>

    // Search and results
    lateinit var fieldRubricSearch: TextField
    lateinit var tableObservations: TableView<Observation>
    lateinit var tableResults: TableView<Result>

    private val currentCaseProperty = SimpleObjectProperty<Case>()

    fun initialize() {
        // Initializing the DatePicker
        fieldDate.value = LocalDate.now()

        // Initializing the ComboBox
        fieldSex.items.addAll(Sex.values())

        // Setting up the 'Observations' table
        tableObservations.columns.run {
            get(0).cellValueFactory = Callback { SimpleStringProperty(it.value.repertory) }
            get(1).cellValueFactory = Callback { SimpleStringProperty(it.value.category) }
            get(2).cellValueFactory = Callback { SimpleStringProperty(it.value.rubric) }
        }

        // Setting up the 'Remedies' table
        tableResults.columns.run {
            get(0).cellValueFactory = Callback { SimpleStringProperty(it.value.medicine) }
            get(1).cellValueFactory = Callback { SimpleIntegerProperty(it.value.marks.toInt()) }
        }

        // Updates the UI according to current case
        currentCaseProperty.addListener { _, _, case -> case.run {
            fieldDate.value = date
            fieldName.text = name
            fieldAge.text = age.toString()
            fieldSex.selectionModel.select(sex)
            tableObservations.items = FXCollections.observableList(observations)
            tableResults.items = FXCollections.observableList(results)
        }}

        // TODO: Creating bindings for 'Save' button
    }

    fun onOpenAction() {
        // TODO: fetch selected case from 'Saved cases' dialog

        currentCaseProperty.set(sampleCase)
    }

    private val sampleCase = get<Case>().apply {
        date = LocalDate.now()
        name = "Foo Bar"
        age = 20
        sex = Sex.MALE
        observations = listOf(
                Observation("Rep11", "P", "W"),
                Observation("Rep11", "Q", "X"),
                Observation("Rep22", "R", "Y"),
                Observation("Rep33", "S", "Z")
        )
        results = listOf(
                Result("Med11" , 10),
                Result("Med22" , 9),
                Result("Med33" , 7),
                Result("Med44" , 7),
                Result("Med55" , 5)
        )
    }
}