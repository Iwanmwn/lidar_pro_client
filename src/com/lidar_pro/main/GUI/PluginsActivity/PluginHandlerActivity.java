package com.lidar_pro.main.GUI.PluginsActivity;

import com.lidar_pro.main.GUI.PluginsActivity.Plugins.SmoothingActivity;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

import java.net.URL;

/**
 * Created by Iwan on 27.07.2016.
 */
public class PluginHandlerActivity extends BorderPane {

    //private final URL fxmlURL = this.getClass().getClassLoader().getResource("res/activities/activity_main.fxml");

    public static MenuBar mainMenu = new MenuBar();

    public PluginHandlerActivity() {

        Menu file = new Menu("Plugin list"); // TODO: get list

        mainMenu.getMenus().addAll(file);
        mainMenu.setMinSize(500.0, 10.0);

        this.setTop(mainMenu);
        this.setCenter(SmoothingActivity.getInstance());
    }

}
