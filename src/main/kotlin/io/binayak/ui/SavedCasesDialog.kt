/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.ui

import io.binayak.model.*
import io.binayak.ui.viewmodel.CaseStudyViewModel
import io.binayak.util.SearchRequest
import io.binayak.util.SearchResponse
import javafx.animation.PauseTransition
import javafx.collections.FXCollections
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.util.Callback
import javafx.util.Duration
import tornadofx.Fragment
import tornadofx.observable
import tornadofx.selectedItem
import java.time.LocalDate

/**
 * Purpose: Dialog for selecting saved cases.
 */
class SavedCasesDialog : Fragment() {
    override val root: Parent by fxml("/fxml/saved-cases-dialog.fxml")

    private val btnOpen: Button by fxid()
    private val fieldCaseSearch: TextField by fxid()
    private val tableSavedCases: TableView<Case> by fxid()

    private val currentCaseStudy: CaseStudyViewModel by inject()

    init {
        subscribe<SearchResponse<Case>> {
            tableSavedCases.items = it.result.observable()
        }

        btnOpen.disableProperty().bind(tableSavedCases.selectionModel.selectedItemProperty().isNull)

        tableSavedCases.columns[0].cellValueFactory = Callback { it.value.dateProperty }
        tableSavedCases.columns[1].cellValueFactory = Callback { it.value.nameProperty }

        initTable()

        // Adding a 100 ms pause between text changes
        val pause = PauseTransition(Duration.millis(100.0))
        fieldCaseSearch.textProperty().addListener { _, _, text ->
            pause.setOnFinished { fire(SearchRequest<Case>(text)) }
            pause.play()
        }
    }

    private fun initTable() {
        tableSavedCases.items.add(Case(
                LocalDate.of(2018, 12, 19),
                "Foo Bar",
                20,
                Sex.MALE,
                FXCollections.observableArrayList(
                        Observation("Rep11", "Ct P", "Rb W"),
                        Observation("Rep11", "Ct Q", "Rb X"),
                        Observation("Rep22", "Ct R", "Rb Y"),
                        Observation("Rep33", "Ct S", "Rb Z")
                ),
                FXCollections.observableArrayList(
                        Result("Med11" , 10),
                        Result("Med22" , 9),
                        Result("Med33" , 7),
                        Result("Med44" , 7),
                        Result("Med55" , 5)
                )
        ))
    }

    fun onOpenAction() {
        println("Opening case")
        currentCaseStudy.item = tableSavedCases.selectedItem
    }
}