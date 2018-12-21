/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.ui.controllers

import io.binayak.model.Case
import io.binayak.model.Observation
import io.binayak.model.Result
import io.binayak.model.Sex
import io.binayak.service.SavedCasesService
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.scene.control.*
import javafx.stage.Stage
import javafx.util.Callback
import javafx.util.StringConverter
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Purpose: Controller for Case study view
 */
class CaseStudyController : KoinComponent {
    // UI
    lateinit var btnOpen: Button
    lateinit var btnSave: Button
    lateinit var fieldDate: DatePicker
    lateinit var fieldName: TextField
    lateinit var fieldAge: TextField
    lateinit var fieldSex: ComboBox<Sex>
    lateinit var fieldRubricSearch: TextField
    lateinit var tableObservations: TableView<Observation>
    lateinit var tableResults: TableView<Result>

    // Others
    private val savedCasesService: SavedCasesService by inject()
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

        savedCasesService.start()

        // TODO: Creating bindings for 'Save' button
    }

    fun onOpenAction() = savedCasesService.showOpenDialog().ifPresent(currentCaseProperty::set)
}