package com.embracesource.traffic.base.utils;

import java.io.*;
import java.util.List;

/**
 * @author ：wangshimin
 * @date ：Created in 2020-11-06 下午 04:53
 * @description：
 * @version:
 */
public class ListCopyUtil {
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
