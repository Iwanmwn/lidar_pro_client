package com.lidar_pro.main.Handlers;

import java.io.*;

/**
 * Created by Iwan on 15.07.2016.
 */
public class FileReader {

    public static Double[][] readDatFileToShow(String fileName) {
        Double arr[][] = new Double[0][];
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            arr = new Double[countLinesInFile(fileName)][2]; // reinit
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                String vars[] = line.split("\\s+");
                arr[index][0] = Double.parseDouble(vars[0]);
                arr[index++][1] = Double.parseDouble(vars[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static int countLinesInFile(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; i++) {
                    if (c[i] == '\n') {
                        count++;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }

    public static double findMaxInDatFile(Double arr[][]) {
        double max = 0.0;
        int chosed = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i][1] > max) {
                max = arr[i][1];
                chosed = i;
            }
        max = arr[chosed][0];
        return max;
    }

    public static double findMinInDatFile(Double arr[][]) {
        double min = findMaxInDatFile(arr);
        int chosen = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i][1] > min) {
                min = arr[i][1];
                chosen = i;
            }
        min = arr[chosen][0];
        return min;
    }

    public static String[] setWavelengthTFieldsFromDatFile(String fileName) {
        String[] s = {String.valueOf(findMinInDatFile(readDatFileToShow(fileName))), String.valueOf(findMaxInDatFile(readDatFileToShow(fileName)))};
        return s;
    }

    public static String getFileDataAsString(String fileName) {
        String s = null;
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                s = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}





















































































