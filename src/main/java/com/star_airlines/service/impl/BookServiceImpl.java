package com.star_airlines.service.impl;

import com.star_airlines.mapper.BookMapper;
import com.star_airlines.pojo.Record;
import com.star_airlines.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public void book_ticket(Record record) {
        bookMapper.boot_ticket(record);
    }
    @Override
    public void refund(String id) {
        bookMapper.refund(id, LocalDateTime.now());
    }
}
