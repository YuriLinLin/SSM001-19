package com.swjd.service;

import com.swjd.bean.Customer;

import java.util.List;

public interface CustomerService {
    //获取所有数据
    public abstract List<Customer> getList();
}
