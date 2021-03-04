package com.simplefileserver.service;

import com.simplefileserver.entity.File;
import com.simplefileserver.entity.FileExample;
import com.simplefileserver.entity.Remote;
import com.simplefileserver.mapper.FileMapper;
import com.simplefileserver.mapper.RemoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private RemoteMapper remoteMapper;

    public void uploadFile(File filedata) {
        File file = fileMapper.selectByPrimaryKey(filedata.getFilename());
        if (file == null) {
            fileMapper.insert(filedata);
        }else {
            fileMapper.updateByPrimaryKey(filedata);
        }
    }

    public List<File> getFileList() {
        FileExample fileExample = new FileExample();
        FileExample.Criteria criteria = fileExample.createCriteria();
        criteria.getAllCriteria();
        List<File> fileList = fileMapper.selectByExample(fileExample);
        return fileList;
    }

    public void deleteFile(String name) {
        fileMapper.deleteByPrimaryKey(name);
    }

    public void uploadRemoteFile(Remote remote) {
        Remote file = remoteMapper.selectByPrimaryKey(remote.getFilename());
        if (file == null) {
            remoteMapper.insert(remote);
        }else {
            remoteMapper.updateByPrimaryKey(remote);
        }
    }

    public Remote getRemoteFile(String fileName) {
        return remoteMapper.selectByPrimaryKey(fileName.replace("remote:",""));
    }
}
