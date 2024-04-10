package org.ningf.ourpetstore.service;

import org.ningf.ourpetstore.entity.Orders;
import org.ningf.ourpetstore.vo.CategoryVO;
import org.ningf.ourpetstore.vo.OrdersVO;
import org.ningf.ourpetstore.vo.UserVO;
import org.ningf.ourpetstore.vo.UsersVO;

public interface HouTaiService {
    public boolean login(String adminname, String adminpassword);
    public boolean register(String adminname, String adminpassword);
    public UserVO getUserById(String userId);
    public UsersVO getUsers();
    public void deleteUser(String userId);
    public void updateUser(UserVO userVO);
    public void createUser(UserVO userVO);
    public OrdersVO getOrders();
    public Orders getOrderById(String orderId);
    public void categoryEdit(CategoryVO categoryVO);
}
