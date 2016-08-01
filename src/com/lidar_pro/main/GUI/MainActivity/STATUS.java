package com.lidar_pro.main.GUI.MainActivity;

/**
 * Created by Iwan on 15.07.2016.
 */
public enum STATUS {

    ONLINE {public String toString() {
            return "Status: Online.";
        }},
    NO_CONNECTION {public String toString() {
            return "Status: Offline. Check your internet connection...";
        }},
    SERVER_OFFLINE { public String toString() {
            return "Status: Offline. Server is not available...";
        } },
    GEN {public String toString() {
            return "Status: Generating...";
        }},
    GEN_ABS {public String toString() {
            return "Status: Generating absorption spectre file...";
        }},
    GEN_SNR {public String toString() {
            return "Status: Generating snr file...";
        }},
    ERROR_EMPTY_INVALID {public String toString() {
            return "Status: Error! Fields should not be empty or invalid.";
        }},
    ERROR_NO_FILE {public String toString() {
            return "Status: Error! File not found...";
        }},
    ERROR_NO_WAVELENGTHS {public String toString() {
            return "Status: Error! Select Off and On wavelengths...";
        }},
    ERROR_IPDA_NOT_AVAILABLE {public String toString() { return "Status: Error! IPDA sounding technique is not available at this time...";}}

}




























































