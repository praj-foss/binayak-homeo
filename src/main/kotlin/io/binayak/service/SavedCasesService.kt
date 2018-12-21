/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.service

import io.binayak.model.Case
import io.binayak.model.Observation
import io.binayak.model.Result
import io.binayak.model.Sex
import io.binayak.ui.controllers.SavedCasesController
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXMLLoader
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import java.time.LocalDate

/**
 * Purpose: Service to access saved cases
 */
// TODO: Extend the JavaFX Service class
class SavedCasesService : Service {
    private var isRunning = false
    private val caseList: ObservableList<Case> = FXCollections.observableArrayList()

    override fun start() {
        if(isRunning) return
        // TODO: Connect to db and refresh table

        caseList.addAll(savedCases)
        isRunning = true
    }

    private val openDialog by lazy { Dialog<Case>().apply {
        title = "Saved case"
        headerText = "Open a case"
        val loader = FXMLLoader(
                SavedCasesService::class.java.classLoader
                        .getResource("fxml/saved-cases-view.fxml")
        )
        dialogPane = loader.load()
        val caseTable = loader.getController<SavedCasesController>().caseTable
        caseTable.items = caseList

        val openButton = ButtonType("Open")
        dialogPane.buttonTypes.add(openButton)
        setResultConverter {
            if(it == openButton)caseTable.selectionModel.selectedItem else null
        }
    }}

    fun showOpenDialog() = checkNotNull(openDialog.showAndWait())

    private val savedCases = mutableListOf(
            Case().apply {
                date = LocalDate.of(2018, 12, 19)
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
    )

    override fun stop() {
        if(!isRunning) return
        // TODO: Disconnect from db
        isRunning = false
    }
}