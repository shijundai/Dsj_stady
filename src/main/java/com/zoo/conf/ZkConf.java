package com.zoo.conf;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/8/27
 */

/**
 *@ClassName ZkConf
 *@Description TODO
 *@Author Administrator
 *@Date 2020/8/27 14:53
 *@Version 1.0
 **/
public class ZkConf {

    public static String zkAddr = "192.168.40.247:2181,192.168.40.247:2182,192.168.40.247:2183,192.168.40.247:2184/myConf";

    /**
     *
     * @param args
     */
    public static void main(String []args) throws InterruptedException {
        MyWatcherCallBack wb = new MyWatcherCallBack(zkAddr);
        wb.exists("/conf");
        MyConf conf = wb.getMyConf();
        while(true) {
            if(conf.getConf() == null) {
                wb.exists("/conf");
            }
            System.out.println(conf.getConf());
            Thread.sleep(3000);
        }
    }

}
