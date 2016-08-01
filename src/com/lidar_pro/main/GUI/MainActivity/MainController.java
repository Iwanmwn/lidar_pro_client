package com.lidar_pro.main.GUI.MainActivity;

import com.lidar_pro.main.Handlers.FileReader;
import com.lidar_pro.main.Handlers.Plot;
import com.lidar_pro.main.Handlers.SocketDialog;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.lidar_pro.main.GUI.MainActivity.MainActivity.statusBar;
import static com.lidar_pro.main.Helpers.GlobalVars.*;

/**
 * Created by Iwan on 25.07.2016.
 */
public class MainController implements Initializable {

    @FXML
    public Text sceneTitle1 = new Text();
    public Text sceneTitle2 = new Text();
    public Text sceneTitle3 = new Text();
    public TextField wav_tField = new TextField();
    public TextField step_tField = new TextField();
    public Button genAbs_btn = new Button();
    public Button showAbsText_btn = new Button();
    public TextField offWav_cm_tField = new TextField();
    public TextField onWav_cm_tField = new TextField();
    public Button showAbsPlot_btn = new Button();
    public RadioButton DIALTechnique_RBtn = new RadioButton();
    public RadioButton IPDATechnique_RBtn = new RadioButton();
    public TextField soundDistance_tField = new TextField();
    public TextField offWav_mkm_tField = new TextField();
    public TextField onWav_mkm_tField = new TextField();
    public Button genSNR_btn = new Button();
    public Button showSNRText_btn = new Button();
    public Button showSNRPlot_btn = new Button();
    public TextArea textArea = new TextArea();
    public Label molecSpectre_label; // = new Label(); ?????
    public Button tAreaClear_btn = new Button();

    public static String spectreMols_text = "";

    private void tArea_appendNewText(Double[][] ar) {
        textArea.clear();
        for (Double[] anAr : ar) {
            textArea.appendText(String.valueOf(anAr[0]) + "  " + String.valueOf(anAr[1]) + "\n");
        }
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // DEBUG FIELDS:
        wav_tField.setText("6500.5");
        step_tField.setText("100");
        DIALTechnique_RBtn.setSelected(true);
        soundDistance_tField.setText("23");
        //*/

        genAbs_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!wav_tField.getText().trim().isEmpty() && !step_tField.getText().trim().isEmpty()) {
                statusBar.setText(STATUS.GEN_ABS.toString());

                String request = "abs" + "," + wav_tField.getText() + "," + step_tField.getText();
                SocketDialog.request_abs(request);

                molecSpectre_label.setText(FileReader.getFileDataAsString(abs_ms_fileName));
                offWav_cm_tField.setText(FileReader.setWavelengthTFieldsFromDatFile(abs_fileName)[0]);
                onWav_cm_tField.setText(FileReader.setWavelengthTFieldsFromDatFile(abs_fileName)[1]);
                tArea_appendNewText(FileReader.readDatFileToShow(abs_fileName));

                statusBar.setText(STATUS.ONLINE.toString());
            } else {
                statusBar.setText(STATUS.ERROR_EMPTY_INVALID.toString());
            }
        });
        wav_tField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                wav_tField.setText(newValue.replaceAll("[^\\d]", "."));
            }
        });
        wav_tField.focusedProperty().addListener((ov, t, t1) -> {
            Platform.runLater(() -> {
                if (wav_tField.isFocused() && !wav_tField.getText().isEmpty()) {
                    wav_tField.selectAll();
                }
            });
        });
        step_tField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                step_tField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        step_tField.focusedProperty().addListener((ov, t, t1) -> {
            Platform.runLater(() -> {
                if (step_tField.isFocused() && !step_tField.getText().isEmpty()) {
                    step_tField.selectAll();
                }
            });
        });
        showAbsText_btn.setOnAction(e -> {
            if (abs_fileName.equals("")) {
                statusBar.setText(STATUS.ERROR_NO_FILE.toString());
            } else {
                textArea.clear();
                FileReader.readDatFileToShow(abs_fileName);
                molecSpectre_label.setText(FileReader.getFileDataAsString(abs_ms_fileName));
                statusBar.setText(STATUS.ONLINE.toString());
                FileReader.setWavelengthTFieldsFromDatFile(abs_fileName);
                tArea_appendNewText(FileReader.readDatFileToShow(abs_fileName));
            }
        });
        offWav_cm_tField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.contains("E")) {
                offWav_cm_tField.setText(newValue.replaceAll("[^\\d]", "."));
                offWav_mkm_tField.setText(String.valueOf(10000000 / Double.parseDouble(offWav_cm_tField.getText())));
            }
        });
        offWav_cm_tField.focusedProperty().addListener((ov, t, t1) -> {
            Platform.runLater(() -> {
                if (offWav_cm_tField.isFocused() && !offWav_cm_tField.getText().isEmpty()) {
                    offWav_cm_tField.selectAll();
                }
            });
        });
        onWav_cm_tField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.contains("E")) {
                onWav_cm_tField.setText(newValue.replaceAll("[^\\d]", "."));
                onWav_mkm_tField.setText(String.valueOf(10000000 / Double.parseDouble(onWav_cm_tField.getText())));
            }
        });
        onWav_cm_tField.focusedProperty().addListener((ov, t, t1) -> {
            Platform.runLater(() -> {
                if (onWav_cm_tField.isFocused() && !onWav_cm_tField.getText().isEmpty()) {
                    onWav_cm_tField.selectAll();
                }
            });
        });
        showAbsPlot_btn.setOnAction(e -> {
            if (abs_fileName.equals("")) {
                statusBar.setText(STATUS.ERROR_NO_FILE.toString());
            } else if (onWav_cm_tField.getText().trim().isEmpty() && offWav_cm_tField.getText().trim().isEmpty()) {
                statusBar.setText(STATUS.ERROR_NO_WAVELENGTHS.toString());
            } else {
                try {
                    Plot.run_abs_plot(abs_fileName, Double.parseDouble(offWav_cm_tField.getText()), Double.parseDouble(onWav_cm_tField.getText()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                statusBar.setText(STATUS.ONLINE.toString());
            }
        });
        DIALTechnique_RBtn.setOnAction(e -> IPDATechnique_RBtn.setSelected(false));
        IPDATechnique_RBtn.setOnAction(e -> {
            DIALTechnique_RBtn.setSelected(false);
            statusBar.setText(STATUS.ERROR_IPDA_NOT_AVAILABLE.toString());
        });
        soundDistance_tField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.contains("E")) {
                soundDistance_tField.setText(newValue.replaceAll("[^\\d]", "."));
            }
        });
        offWav_mkm_tField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                if (!newValue.contains("E")) {
                    offWav_mkm_tField.setText(newValue.replaceAll("[^\\d]", "."));
                }
            }
        });
        offWav_mkm_tField.focusedProperty().addListener((ov, t, t1) -> {
            Platform.runLater(() -> {
                if (offWav_mkm_tField.isFocused() && !offWav_mkm_tField.getText().isEmpty()) {
                    offWav_mkm_tField.selectAll();
                }
            });
        });
        onWav_mkm_tField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.contains("E")) {
                onWav_mkm_tField.setText(newValue.replaceAll("[^\\d]", "."));
            }
        });
        onWav_mkm_tField.focusedProperty().addListener((ov, t, t1) -> {
            Platform.runLater(() -> {
                if (onWav_mkm_tField.isFocused() && !onWav_mkm_tField.getText().isEmpty()) {
                    onWav_mkm_tField.selectAll();
                }
            });
        });

        genSNR_btn.setOnAction(e -> {
            //MainController.textArea.clear();
            statusBar.setText(STATUS.GEN_SNR.toString());

            String request = "snr" + "," + onWav_mkm_tField.getText() + "," + offWav_mkm_tField.getText() + "," + soundDistance_tField.getText();
            if (!smoothing_coef.equals("")) {
                request += "," + smoothing_prefix + smoothing_coef;
            }
            if (!calibration_coef.equals("")) {
                request += "," + calibration_coef_prefix + calibration_coef;
            }
            SocketDialog.request_snr(request);

            tArea_appendNewText(FileReader.readDatFileToShow(snr_fileName));
            statusBar.setText(STATUS.ONLINE.toString());
        });

        showSNRText_btn.setOnAction(e -> {
            if (snr_fileName.equals("")) {
                statusBar.setText(STATUS.ERROR_NO_FILE.toString());
            } else if (onWav_cm_tField.getText().trim().isEmpty() && offWav_cm_tField.getText().trim().isEmpty()) {
                statusBar.setText(STATUS.ERROR_NO_WAVELENGTHS.toString());
            } else {
                //MainController.textArea.clear();
                FileReader.readDatFileToShow(snr_fileName);
                statusBar.setText(STATUS.ONLINE.toString());
                FileReader.setWavelengthTFieldsFromDatFile(snr_fileName);
                tArea_appendNewText(FileReader.readDatFileToShow(snr_fileName));
            }
        });

        showSNRPlot_btn.setOnAction(e -> {
            if (snr_fileName.equals("")) {
                statusBar.setText(STATUS.ERROR_NO_FILE.toString());
            } else {
                try {
                    Plot.run_snr_plot(snr_fileName);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                statusBar.setText(STATUS.ONLINE.toString());
            }
        });
        tAreaClear_btn.setOnAction(e -> {
            molecSpectre_label.setText("");
            textArea.clear();
        });
    }

}
