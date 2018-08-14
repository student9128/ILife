package com.kevin.live.util;

import android.os.Environment;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/4/12.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class FileUtils {
    private static String TAG = "FileUtils.class";
    /**
     * directory
     */
    public static String directory = Environment.getExternalStorageDirectory() +
            File.separator + "Life" + File.separator;

    /**
     * get info about whether sd is mounted.
     *
     * @return
     */
    public static boolean isSDMounted() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * create file directory.
     *
     * @param name
     * @return
     */
    public static File createFileDirectory(String name) {
        File destDir = new File(directory + name);
        boolean mkdirs = destDir.mkdirs();
        LogK.i(TAG,"==="+mkdirs);
        return destDir;
    }

    /**
     * get info about whether file is exit.
     *
     * @param fileName
     * @return
     */
    public static boolean isFileExist(String fileName) {
        File file = new File(directory + fileName);
        file.isFile();
        return file.exists();
    }

    public static String readFileFromSdcard(String fileName) throws IOException {
        String res = "";
        try {
            FileInputStream fis = new FileInputStream(directory + fileName);

            int length = fis.available();

            byte[] buffer = new byte[length];
            fis.read(buffer);

            res = EncodingUtils.getString(buffer, "UTF-8");

            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;


//        FileInputStream in = null;
//        try {
//            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
//            // 一次读多个字节
//            byte[] tempbytes = new byte[100];
//            int byteread = 0;
//            in = new FileInputStream(fileName);
////            ReadFromFile.showAvailableBytes(in);
//            // 读入多个字节到字节数组中，byteread为一次读入的字节数
//            while ((byteread = in.read(tempbytes)) != -1) {
//                System.out.write(tempbytes, 0, byteread);
//            }
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e1) {
//                }
//            }
    }

    /**
     * save file & data to SDCard.
     *
     * @param fileName
     * @param data
     */
    public static void saveFile2Sdcard(String fileName, String data) {
        try {
            File f = new File(directory + fileName,"hello.txt");
//            if (f.exists()) {
//                f.delete();
//            }
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            byte[] bytes = data.getBytes();

            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete all the files.
     */
    public static void deleteDir() {
        File dir = new File(directory);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete();
            else if (file.isDirectory())
                deleteDir();
        }
        dir.delete();
    }


    public static boolean createFile(String fileName,String filecontent){
        Boolean bool = false;
//       String filenameTemp = directory+fileName+".txt";//文件路径+名称+文件类型
       String filenameTemp = directory+fileName+".txt";//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is "+filenameTemp);
                //创建文件成功后，写入内容到文件里
                writeFileContent(filenameTemp, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;
    }
    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr  写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath,String newstr) throws IOException{
        Boolean bool = false;
        String filein = newstr+"\r\n";//新写入的行，换行
        String temp  = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 删除文件
     * @param fileName 文件名称
     * @return
     */
    public static boolean delFile(String fileName){
        Boolean bool = false;
        String  filenameTemp= directory+fileName+".txt";
        File file  = new File(filenameTemp);
        try {
            if(file.exists()){
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
        }
        return bool;
    }
}
