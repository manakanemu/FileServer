package com.simplefileserver.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    private String root = "/home/server";
//    private String root = "/Users/zhang/Tempfile";
    private String dir = "/file";
    public FileUtils(){
        File path = new File(getFullPath());
        if(!path.exists()){
            path.mkdirs();
        }
    }
    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    public String getFullPath(){
     return root+dir;
    }

    public boolean saveFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        File saveFile = new File(getFullPath()+"/"+filename);
        try {
            file.transferTo(saveFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
