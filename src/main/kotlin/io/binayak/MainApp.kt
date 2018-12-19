/**
 * This file is licensed under the MIT license.
 * See the LICENSE file in project root for details.
 */

package io.binayak

import io.binayak.util.DependencyInjection.injectionModules
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext

/**
 * Purpose: Main application class
 */
class MainApp : Application(), KoinComponent {
    private lateinit var stage: Stage

    // The entry point
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(MainApp::class.java, *args)
        }
    }

    // Called before stage creation
    override fun init() {
        super.init()
        StandAloneContext.startKoin(injectionModules)
    }

    // Called after stage creation
    override fun start(primaryStage: Stage?) {
        stage = checkNotNull(primaryStage) { "ERROR: null value passed into primaryStage" }

        val root = FXMLLoader().load<Parent>(
                javaClass.classLoader.getResourceAsStream("fxml/case-study-view.fxml")
        )

        stage.run {
            title = "${Values.APP_NAME} - ${Values.APP_VERSION}"
            minWidth = 800.0
            minHeight = 480.0
            scene = Scene(root)
            show()
        }
    }

    // Called when app stops
    override fun stop() {
        super.stop()
        stage.close()
        StandAloneContext.stopKoin()
    }
}