package com.dmsd.api;

import com.dmsd.pojo.Orders;
import com.dmsd.pojo.OrdersUserModel;

import java.util.List;

/**
 * Created by Ares on 2018/3/6.
 */
public interface OrderService {
    public List<Orders> queryOrdersAndOrderDetailAndItems();

    List<Orders> queryOrdersAndOrderDetailMap();

    List<Orders> queryOrderMap();

    List<OrdersUserModel> queryOrdersAndUserModel();

}
