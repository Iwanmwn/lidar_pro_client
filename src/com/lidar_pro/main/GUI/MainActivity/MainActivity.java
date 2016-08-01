package com.lidar_pro.main.GUI.MainActivity;

import com.lidar_pro.main.GUI.PluginsActivity.PluginHandlerActivity;
import com.lidar_pro.main.Handlers.SocketDialog;
import com.lidar_pro.main.Helpers.GlobalVars;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import org.controlsfx.control.StatusBar;

import java.net.URL;

import static com.lidar_pro.main.Helpers.GlobalVars.ip;
import static com.lidar_pro.main.Helpers.GlobalVars.port;
import static com.lidar_pro.main.Helpers.GlobalVars.windowName;

/**
 * Created by Iwan on 15.07.2016.
 */
public class MainActivity extends Application {

    private final URL fxmlURL = this.getClass().getClassLoader().getResource("res/activities/activity_main.fxml");

    public static PluginHandlerActivity pluginHandlerActivity;
    public static StatusBar statusBar;

    public SocketDialog socketDialog = new SocketDialog(ip, port);

    public void startThread(Stage stage) throws Exception { // bad construction idea???

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(1)); // is it normal insets???

        GridPane mainGrid = FXMLLoader.load(fxmlURL);
        pluginHandlerActivity = new PluginHandlerActivity();
        statusBar = new StatusBar();
        statusBar.setText(STATUS.ONLINE.toString());
        // TODO: add this - statusBar.setProgress(.9); // .99 - 99%
        statusBar.setMinWidth(GlobalVars.window_width);
        HBox hbStatus = new HBox(10);
        hbStatus.setAlignment(Pos.BOTTOM_LEFT); // BOTTOM_CENTER
        hbStatus.getChildren().add(statusBar);

        border.setLeft(mainGrid);
        border.setRight(pluginHandlerActivity);
        border.setBottom(hbStatus);

        Scene scene = new Scene(border, GlobalVars.window_width, GlobalVars.window_height);
        stage.setScene(scene);
        stage.setTitle(windowName);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception { // Program entry point
        startThread(stage);

        socketDialog.start();
    }

    @Override
    public void stop() {
        //socketDialog.stop(); // DEPRECATED METHOD WONT STOP
        System.exit(0); // TODO: RETHINK IT
    }

}

































































