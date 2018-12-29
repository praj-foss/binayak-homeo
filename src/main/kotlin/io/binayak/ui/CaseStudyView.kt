/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.ui

import com.sun.javafx.scene.control.skin.TableHeaderRow
import io.binayak.model.Observation
import io.binayak.model.Result
import io.binayak.model.Sex
import io.binayak.ui.viewmodel.CaseStudyViewModel
import io.binayak.util.SearchRequest
import javafx.animation.PauseTransition
import javafx.scene.Parent
import javafx.scene.control.*
import javafx.util.Callback
import javafx.util.Duration
import javafx.util.converter.NumberStringConverter
import tornadofx.View
import tornadofx.filterInput
import tornadofx.isInt

/**
 * Purpose: View for current case.
 */
class CaseStudyView : View() {
    override val root: Parent by fxml("/fxml/case-study-view.fxml")

    private val btnOpen: Button by fxid()
    private val btnSave: Button by fxid()
    private val fieldDate: DatePicker by fxid()
    private val fieldName: TextField by fxid()
    private val fieldAge: TextField by fxid()
    private val fieldSex: ComboBox<Sex> by fxid()
    private val tableObservations: TableView<Observation> by fxid()
    private val tableResults: TableView<Result> by fxid()
    private val fieldRubricSearch: TextField by fxid()

    private val currentCaseStudy: CaseStudyViewModel by inject()

    init {
        // Setting up the components
        btnSave.disableProperty().bind(
                currentCaseStudy.dirty.not()
                        .or(fieldDate.valueProperty().isNull)
                        .or(fieldName.textProperty().isEmpty))

        fieldDate.valueProperty().bindBidirectional(currentCaseStudy.date)
        fieldName.textProperty().bindBidirectional(currentCaseStudy.name)

        fieldAge.filterInput { it.controlNewText.isInt() }
        fieldAge.textProperty().bindBidirectional(currentCaseStudy.age, NumberStringConverter())

        fieldSex.items.addAll(Sex.values())
        fieldSex.valueProperty().bindBidirectional(currentCaseStudy.sex)

        // Adding a 100 ms pause between text changes
        val pause = PauseTransition(Duration.millis(100.0))
        fieldRubricSearch.textProperty().addListener { _, _, text ->
            pause.setOnFinished { fire(SearchRequest<Observation>(text)) }
            pause.play()
        }

        // TODO: Add custom cell design to tables with remove buttons
        tableObservations.run {
            columns[0].cellValueFactory = Callback { it.value.repertoryProperty }
            columns[1].cellValueFactory = Callback { it.value.categoryProperty }
            columns[2].cellValueFactory = Callback { it.value.rubricProperty }
            disableColumnReordering()
            itemsProperty().bindBidirectional(currentCaseStudy.observations)
        }

        tableResults.run {
            columns[0].cellValueFactory = Callback { it.value.medicineProperty }
            columns[1].cellValueFactory = Callback { it.value.marksProperty }
            disableColumnReordering()
            itemsProperty().bindBidirectional(currentCaseStudy.results)
        }
    }

    fun onOpenAction() {
        find<SavedCasesDialog>().openModal()
    }

    fun onSaveAction() {
        // TODO: Add validation logic to view-model
        currentCaseStudy.commit()
    }

    // Disables column reordering using an extension function
    private fun <S> TableView<S>.disableColumnReordering() {
        this.skinProperty().addListener { _, _, _ ->
            val header = lookup("TableHeaderRow") as TableHeaderRow
            //header.reorderingProperty().bind(SimpleBooleanProperty(false))
            header.reorderingProperty().addListener { _ -> header.isReordering = false }
        }
    }
    // TODO: Replace the above hack
}