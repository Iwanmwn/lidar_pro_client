package com.lidar_pro.main.Helpers;

/**
 * Created by Iwan on 26.07.2016.
 */
public class GlobalVars {

    /* Window:
     _______________________________________
    |_LWCH____________________________|-|O|X|    ////
    |//abs//////tArea/////////|__choose_____|    ////
    |/////////////////////////|//plugin/////|    ////
    |/////////////////////////|/////////////|    ^
    |/////////////////////////|/////////////|
    |//snr////////////////////|/////////////|    window_height
    |/////////////////////////|/////////////|
    |/////////////////////////|/////////////|    ,
    |/////////////////////////|/////////////|    ////
    |__statusBar____________________________|    ////

    ////////// <- window_width -> ///////////
    // < mainArea_width > ////
                               pluginArea_width
    */

    // GUI:

    public static final int mainArea_width = 570;
    public static final int pluginArea_width = 330;
    public static final int window_width = mainArea_width + pluginArea_width; // = 850
    public static final int window_height = 520;

    public static final String windowName = "LWCH (DIAL/IPDA)";

    // Network:

    public static final String ip = "localhost";
    public static final int port = 9096;

    // Variables [files]:

    public static final String sharedPath = "C:/ivan_temp/WIP/Development/IdeaProjects/lidar_pro_server/"; // path (folder on cd or link on web) to shared location with generated files

    public static String abs_fileName = "";
    public static String abs_ms_fileName = "";
    public static String snr_fileName = "";

    public static String fileExtension_dat = ".dat";

    // plugin variables:

    public static String smoothing_coef = "";
    public static String calibration_coef = "";

    // plugin prefix codes:
// TODO: перенести префиксы в загружаемый файл для парса сервером и клиентом, чтобы не синхронизировать постоянно в коде
    public static String smoothing_prefix = "smth";
    public static String calibration_coef_prefix = "cali";

}
