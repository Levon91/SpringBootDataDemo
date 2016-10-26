package com.energizeglobal.common;

import com.energizeglobal.common.exception.ServerErrorException;

import java.io.File;
import java.io.IOException;

/**
 * Created by test on 7/26/2016.
 */
public interface Constants {


    static String getPath() {
        try {
            return new File(".").getCanonicalPath();
        } catch (IOException e) {
            throw new ServerErrorException("Can't get application path", e);
        }
    }


    String STORAGE_DIR_PATH = "\\web\\src\\main\\resources\\static\\data\\";
    String FULL_STORAGE_PATH = getPath() + STORAGE_DIR_PATH;
}
