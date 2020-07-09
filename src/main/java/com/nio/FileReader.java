package com.nio;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/24
 */

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *@ClassName FileReader
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/24 11:14
 *@Version 1.0
 **/
public class FileReader {


    public static void testRaf() {
        try {
            RandomAccessFile raf = new RandomAccessFile("raf.txt","rw");
            FileChannel fileChannel = raf.getChannel();
            MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 8192);
//            ByteBuffer map = ByteBuffer.allocateDirect(8192);
            long begin = System.currentTimeMillis();
            while(System.currentTimeMillis() - begin < 1000) {
                while(map.position() < 8000) {
                    map.put("0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n".getBytes());
                }
                map.flip();
                fileChannel.write(map);
                map.compact();
            }
            fileChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testBuffer() {
        try {
            OutputStream os = new FileOutputStream("fos.txt");
            BufferedOutputStream bos = new BufferedOutputStream(os);
            long begin = System.currentTimeMillis();
            while(System.currentTimeMillis() - begin < 1000) {
                bos.write("0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n0123456789\n".getBytes());
            }
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[]args) {
        testRaf();
        testBuffer();
    }

}
