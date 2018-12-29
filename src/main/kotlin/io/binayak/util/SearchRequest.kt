/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.util

import tornadofx.EventBus.RunOn
import tornadofx.FXEvent

/**
 * Purpose: Event for initiating a search. Runs on background thread.
 */
class SearchRequest<T>(val text: String) : FXEvent(RunOn.BackgroundThread)