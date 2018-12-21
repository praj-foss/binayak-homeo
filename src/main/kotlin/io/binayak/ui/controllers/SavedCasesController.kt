/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.ui.controllers

import io.binayak.model.Case
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.TableView
import javafx.util.Callback
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Purpose: Controller for Saved cases view
 */
class SavedCasesController {
    // UI
    lateinit var caseTable: TableView<Case>

    fun initialize() {
        // Table
        caseTable.columns.run {
            get(0).cellValueFactory = Callback {
                SimpleStringProperty(it.value.date!!.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)))
            }
            get(1).cellValueFactory = Callback { SimpleStringProperty(it.value.name!!) }
            get(2).cellValueFactory = Callback { SimpleIntegerProperty(it.value.age!!) }
            get(3).cellValueFactory = Callback { SimpleStringProperty(it.value.sex!!.toString()) }
        }
    }
}