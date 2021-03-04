package com.simplefileserver.controller;

import com.google.gson.Gson;
import com.simplefileserver.entity.File;
import com.simplefileserver.entity.Remote;
import com.simplefileserver.entity.ResultFile;
import com.simplefileserver.service.FileService;
import com.simplefileserver.service.RequestService;
import com.simplefileserver.utils.AuthUtils;
import com.simplefileserver.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private RequestService requestService;

    @ResponseBody
    @GetMapping("/filelist")
    public String getFileList() {
        List<File> fileList = fileService.getFileList();
        FileUtils fileUtils = new FileUtils();
        List<ResultFile> resultFiles = new ArrayList<ResultFile>();
        for (File file : fileList) {
            ResultFile resultFile = new ResultFile();
            if (file.getFilename().startsWith("remote:")) {
                resultFile.setName(file.getFilename().replace("remote:",""));
            } else {
                resultFile.setName(file.getFilename());
            }
            resultFile.setUrl(fileUtils.getDir() + "/" + file.getFilename());
            resultFile.setDownloadTimes(requestService.getDownloadTimes(resultFile.getName()));
            resultFiles.add(resultFile);

        }
        Gson gson = new Gson();
        return gson.toJson(resultFiles);
    }

    @GetMapping("/deletefile")
    public String deleteFile(String name) {
        fileService.deleteFile(name);
        return "redirect:/console";
    }

    @ResponseBody
    @GetMapping("/fullfilelist")
    public String getFullFileList(HttpServletRequest request) {
        if (AuthUtils.isLogin(request)) {
            List<File> fileList = fileService.getFileList();
            FileUtils fileUtils = new FileUtils();
            Gson gson = new Gson();
            return gson.toJson(fileList);
        } else {
            return "";
        }

    }

    @ResponseBody
    @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request, MultipartFile file) {
        if (AuthUtils.isLogin(request)) {
            FileUtils fileUtils = new FileUtils();
            fileUtils.saveFile(file);
            File filedata = new File();
            filedata.setFilename(file.getOriginalFilename());
            filedata.setTime(new Timestamp(new Date().getTime()));
            fileService.uploadFile(filedata);
        }
        return "";
    }

    @ResponseBody
    @GetMapping("/addRemoteFile")
    public String uploadRemoteFile(HttpServletRequest request, Remote remote) {
        if (AuthUtils.isLogin(request)) {
            File filedata = new File();
            filedata.setFilename("remote:" + remote.getFilename());
            filedata.setTime(new Timestamp(new Date().getTime()));
            fileService.uploadFile(filedata);
            fileService.uploadRemoteFile(remote);
        }
        return "";
    }
}
