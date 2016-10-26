package com.energizeglobal.common;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.ws.rs.ServerErrorException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by test on 7/26/2016.
 */
public class FileUtil {

    public static void createBookFolderById(String dirPath) {
        try {
            Path path = Paths.get(dirPath );
            Files.createDirectories(path);
//            Path imagePath = Paths.get(path + "/img");
//            Path pdfPath = Paths.get(path + "/pdf");
//            Files.createDirectories(imagePath);
//            Files.createDirectories(pdfPath);
        } catch (IOException e) {
            throw new ServerErrorException(500);
        }
    }

    public static void saveFile(InputStream inputStream, String path, String fileName) {

        String endPath = path + fileName;
        OutputStream out = null;
        try {
            out = new FileOutputStream(endPath);
            IOUtils.copy(inputStream, out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
