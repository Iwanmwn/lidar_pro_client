package com.lidar_pro.main.GUI.PluginsActivity.Plugins;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Iwan on 26.07.2016.
 */
public class SmoothingActivity extends BorderPane {

    private static SmoothingActivity ourInstance = new SmoothingActivity();
    public static SmoothingActivity getInstance() {
        return ourInstance;
    }

    /* * * * * * * * * */

    private final URL fxmlURL = this.getClass().getClassLoader().getResource("res/activities/activity_plugin_smoothing.fxml");
    private GridPane mainGrid = null;

    private SmoothingActivity() {
        try {
            mainGrid = FXMLLoader.load(fxmlURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setTop(mainGrid);
    }

}