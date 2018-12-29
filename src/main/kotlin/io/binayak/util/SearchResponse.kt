/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak.util

import tornadofx.FXEvent

/**
 * Purpose: Event for receiving search results.
 */
class SearchResponse<T>(val result: List<T>) : FXEvent()