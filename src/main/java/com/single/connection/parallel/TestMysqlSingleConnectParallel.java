package com.single.connection.parallel;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/11
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *@ClassName TestMysqlSingleConnectParallel
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/11 9:30
 *@Version 1.0
 **/
public class TestMysqlSingleConnectParallel {

    private static volatile Connection conn;
    private static volatile Statement statement;

    private static Connection getConn() {
        if(conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://192.168.40.92:3306/cnbot_framework?useUnicode=true&characterEncoding=UTF-8", "root", "Passw0rd");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }


    public static Statement getStatement() {
        if(statement == null) {
            try {
                statement = getConn().createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    public static void executeSql(String sql,String outFile) {
        Statement statement = null;
        try {
            statement = getStatement();
            ResultSet result = statement.executeQuery(sql);
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<String> columNames = new ArrayList<>(columnCount);
            for(int i=1;i<=columnCount;i++) {
                columNames.add(metaData.getColumnName(i));
//                //字段类型
//                int columnType = metaData.getColumnType(i);
//                //字段长度
//                int type = metaData.getPrecision(i);
//                String columnClass = metaData.getColumnClassName(i);
//                System.out.println(metaData.getColumnName(i) + " " + columnClass + " " + columnType +" " +type);
            }
            FileOutputStream fos = new FileOutputStream(outFile);
            while (result.next()) {
//                map.clear();
                columNames.forEach( cn -> {
                    try {
                        String value = "\t" + cn + ":" +result.getString(cn) + "\t";
                        fos.write(value.getBytes());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                fos.write("\n".getBytes());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args) {

//        executeSql("select cu.`real_name`,@rk:=@rk+1 rk,@rt:=current_timestamp(3) rt from cnbots_user cu", "1.txt");

        for(int i=0;i<50;i++) {
            int finalI = i;
            new Thread(()->{
                executeSql("select cu.`real_name`,@rk:=@rk+1 rk,@rt:=current_timestamp(3) rt from cnbots_user cu,(select @rk := 0,@rt) over;", finalI +".txt");
            }).start();

//            //报错 无论多少线程 sql是顺序灌入的
//            new Thread(()->{
//                executeSql("select @rk := 0 rk,@rt rt","start.txt");
//                executeSql("select cu.`real_name`,@rk:=@rk+1 rk,@rt:=current_timestamp(3) rt from cnbots_user cu", "1.txt");
//            }).start();

        }
        try {
            getConn().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
