package kr.itanoss.cryptoexample;

import java.io.*;

public class Utility {

    public static void execShell(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        BufferedReader shellCommandReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String currentLine;
        while ((currentLine = shellCommandReader.readLine()) != null) {
            System.out.println(currentLine);
        }
    }

    public static byte[] readBytes(String filepath) throws IOException {
        File f = new File(filepath);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int)f.length()];
        dis.readFully(keyBytes);
        dis.close();
        return keyBytes;
    }
}
