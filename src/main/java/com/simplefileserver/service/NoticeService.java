package com.simplefileserver.service;

import com.simplefileserver.entity.Notice;
import com.simplefileserver.entity.NoticeExample;
import com.simplefileserver.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    public void addNotice(Notice notice) {
        noticeMapper.insert(notice);
    }

    public List<Notice> getNotice() {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        criteria.getAllCriteria();
        List<Notice> noticeList = noticeMapper.selectByExample(example);
        noticeList.sort(Comparator.comparingInt(Notice::getLevel));
        return noticeList;
    }

    public void deleteNotice(Notice notice) {
        String primaryKey = notice.getNumber();
        noticeMapper.deleteByPrimaryKey(primaryKey);
        int a = 1;
    }
}
