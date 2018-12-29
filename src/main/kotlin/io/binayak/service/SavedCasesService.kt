/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.service

import io.binayak.model.Case
import io.binayak.util.SearchRequest
import io.binayak.util.SearchResponse
import tornadofx.Controller

/**
 * Purpose: Service to access saved cases.
 */
class SavedCasesService : Controller() {
    init {
        subscribe<SearchRequest<Case>> {
            searchCase(it.text)
        }
    }

    private fun searchCase(text: String) {
        // TODO: Perform a full-text search here
        fire(SearchResponse<Case>(emptyList()))
    }
}