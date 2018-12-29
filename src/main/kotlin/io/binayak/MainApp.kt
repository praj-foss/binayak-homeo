/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak

import io.binayak.service.SavedCasesService
import io.binayak.ui.CaseStudyView
import tornadofx.App
import tornadofx.find

/**
 * Purpose: Main application class.
 */
class MainApp : App(CaseStudyView::class) {
    init {
        // Activating all controllers
        find(SavedCasesService::class)
    }
}