package org.ningf.ourpetstore.service;

import org.ningf.ourpetstore.entity.Orders;
import org.ningf.ourpetstore.vo.*;

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

}
