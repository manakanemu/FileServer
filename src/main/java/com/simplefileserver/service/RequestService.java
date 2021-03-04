package com.simplefileserver.service;

import com.simplefileserver.entity.Request;
import com.simplefileserver.entity.RequestExample;
import com.simplefileserver.entity.RequestStatistics;
import com.simplefileserver.entity.ResultInfo;
import com.simplefileserver.mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RequestService {
    @Autowired
    RequestMapper requestMapper;

    public void recordRequest(Request request) {
        requestMapper.insert(request);
    }

    public List<Request> getAllRequest() {
        RequestExample example = new RequestExample();
        RequestExample.Criteria criteria = example.createCriteria();
        criteria.getAllCriteria();
        List<Request> requestList = requestMapper.selectByExample(example);
        return requestList;
    }

    public int getDownloadTimes(String name) {
        RequestExample example = new RequestExample();
        RequestExample.Criteria criteria = example.createCriteria();
        criteria.getAllCriteria();
        criteria.andFilenameLike("%" + name);
        List<Request> requestList = requestMapper.selectByExample(example);
        Set<String> ipSet = new HashSet<String>();
        if (requestList == null) {
            return 0;
        } else {
            int times = 0;
            for (Request request : requestList) {
                ipSet.add(request.getIp());
            }
            return ipSet.size();
        }
    }

    public List<ResultInfo> getRequestStatistics() {
        RequestExample example = new RequestExample();
        RequestExample.Criteria criteria = example.createCriteria();
        criteria.getAllCriteria();
        List<Request> requestList = requestMapper.selectByExample(example);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Integer> reqdic = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        List<RequestStatistics> statisticsList = new ArrayList<>();
        for (Request request : requestList) {
            String sdate = sdf.format(request.getTime());
            if (reqdic.containsKey(sdate)) {
                reqdic.put(sdate, reqdic.get(sdate) + 1);
            } else {
                reqdic.put(sdate, 1);
            }
        }

        Set<String> keys = reqdic.keySet();
        List<ResultInfo> sortStatisticsList = new ArrayList<>();
        for(String key:keys){
            ResultInfo info = new ResultInfo();
            info.setMsg(key);
            info.setStatus(reqdic.get(key));
            sortStatisticsList.add(info);
        }
        return sortStatisticsList;

    }
}
