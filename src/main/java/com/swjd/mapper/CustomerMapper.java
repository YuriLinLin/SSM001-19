package com.swjd.mapper;

import com.swjd.bean.Customer;

import java.util.List;

public interface CustomerMapper {
    //查询全部
    public  abstract List<Customer> getAll();
}
