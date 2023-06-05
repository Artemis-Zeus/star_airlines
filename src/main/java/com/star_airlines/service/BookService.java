package com.star_airlines.service;

import com.star_airlines.pojo.Record;

public interface BookService {
    void book_ticket(Record record);

    void refund(String id);
}
