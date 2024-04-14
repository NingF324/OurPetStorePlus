package org.ningf.ourpetstore.service;

import org.ningf.ourpetstore.entity.Orders;
import org.ningf.ourpetstore.entity.Orderstatus;
import org.ningf.ourpetstore.vo.*;

import java.util.List;

public interface HouTaiService {
    public boolean login(String adminname, String adminpassword);
    public boolean register(String adminname, String adminpassword);
    public UserVO getUserById(String userId);
    public UsersVO getUsers();
    public boolean deleteUser(String userId);
    public boolean updateUser(UserVO userVO);
    public boolean createUser(UserVO userVO);
    public OrdersVO getOrders();
    public Orders getOrderById(String orderId);
    public boolean updateOrder(OrderVO ordersVO);
    public boolean deleteOrder(String orderId);
    public boolean launchItem(String itemId);
    public boolean delistItem(String itemId);
    public Orderstatus getOrderStatusById(String orderId);
    public List<Orders> searchOrders(String keyword);
}
