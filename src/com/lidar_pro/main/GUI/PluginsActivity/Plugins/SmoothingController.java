package com.lidar_pro.main.GUI.PluginsActivity.Plugins;

import com.lidar_pro.main.GUI.MainActivity.STATUS;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import kotlin.reflect.jvm.internal.impl.util.Check;

import static com.lidar_pro.main.GUI.MainActivity.MainActivity.statusBar;
import static com.lidar_pro.main.Helpers.GlobalVars.smoothing_coef;

/**
 * Created by Iwan on 26.07.2016.
 */
public class SmoothingController {

    @FXML
    public TextField smoothCoef_tField = new TextField();
    public CheckBox enable_cb = new CheckBox();

    @FXML
    public void initialize() {

        smoothCoef_tField.setText("8");

        // TODO: сделать так, чтобы в текстфилде никогда не было пусто
        // TODO: сделать так, чтобы при изменении текстфилда, коэффициент менялся динамически
        // TODO: сделать так, чтобы при вводе текста в тфилд считывались только цифры
        enable_cb.setOnAction(e -> {
            if (enable_cb.isSelected()) {
                smoothing_coef = smoothCoef_tField.getText();
                System.out.println(smoothing_coef);
            } else {
                smoothing_coef = "0";
                System.out.println(smoothing_coef);
            }
        });
    }

}
