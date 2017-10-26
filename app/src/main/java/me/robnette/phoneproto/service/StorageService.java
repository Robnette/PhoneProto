package me.robnette.phoneproto.service;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static java.io.File.separator;

/**
 * Created by etienne.clercboichut on 25/10/2017.
 */

public class StorageService {

    public static boolean isData(Context context, String fileName){
        File file = new File(context.getFilesDir()+ separator + fileName);
        return  file.exists();
    }

    public static <T> T  loadData(Context context, String fileName, Class clazz){
//        ObjectMapper objectMapper = new ObjectMapper();

        Gson gson = new GsonBuilder().create();

        try {
            Log.d("----fileName---LOAD" , fileName);
            FileInputStream fileInputStream = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            return (T) gson.fromJson(reader, clazz);
//            return (T) objectMapper.readValue(fileInputStream,  clazz );
        } catch (Exception e) {
            e.printStackTrace();
            context.deleteFile(fileName);
            throw new RuntimeException("Error loading data");
        }
    }

//    public  static File loadFile(File file, String fileName){
//
//        try {
//            Log.d("----fileName---LOAD" , fileName);
//            return new File(file+separator +fileName);
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error loading file");
//        }
//
//
//    }
}
