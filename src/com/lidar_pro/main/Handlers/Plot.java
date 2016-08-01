package com.lidar_pro.main.Handlers;

import java.io.IOException;

/**
 * Created by Iwan on 28.07.2016.
 */
public class Plot {

    public static void run_abs_plot(String fileName, Double lam1, Double lam2) throws IOException {
        new ProcessBuilder("gen\\abs_fast_plot.py" + fileName, lam1.toString(), lam2.toString()).start();
    }

    public static void run_snr_plot(String fileName) throws IOException {
        new ProcessBuilder("gen\\snr_fast_plot.py" + fileName).start();
    }

}
