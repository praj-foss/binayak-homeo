/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.ui.viewmodel

import io.binayak.model.Case
import io.binayak.model.Observation
import io.binayak.util.SearchResponse
import javafx.collections.FXCollections
import tornadofx.ItemViewModel
import tornadofx.observable

/**
 * Purpose: ViewModel for case study view.
 */
class CaseStudyViewModel : ItemViewModel<Case>() {
    val date = bind(Case::dateProperty)
    val name = bind(Case::nameProperty)
    val age = bind(Case::ageProperty)
    val sex = bind(Case::sexProperty)
    val observations = bind(Case::observationsProperty)
    val results = bind(Case::resultsProperty)

    init {
        subscribe<SearchResponse<Observation>> {
            item.observationsProperty.set(it.result.observable())
        }
    }

    override fun onCommit() {
        // TODO: Save case to the database
        println("Saved new case to the database")
    }
}