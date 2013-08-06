package com.szwx.yht.util;

import com.szwx.yht.exception.HrsExpression;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: szwx
 * Date: 13-6-15
 * Time: 下午1:08
 * To change this template use File | Settings | File Templates.
 */
public class ReadFile {
    public static String read(String file) {
        InputStream is = ReadFile.class.getClassLoader().getResourceAsStream(file);
        try {
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new HrsExpression(e);
        }
    }
}
