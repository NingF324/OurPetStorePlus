package org.ningf.ourpetstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ningf.ourpetstore.entity.*;
import org.ningf.ourpetstore.persistence.*;
import org.ningf.ourpetstore.service.HouTaiService;
import org.ningf.ourpetstore.vo.CategoryVO;
import org.ningf.ourpetstore.vo.OrdersVO;
import org.ningf.ourpetstore.vo.UserVO;
import org.ningf.ourpetstore.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/3/28 23:22
 */
@Service("houTaiService")
public class HoutaiServiceImpl implements HouTaiService {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private SignonMapper signonMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public boolean login(String adminname, String adminpassword) {
        Admin admin =adminMapper.selectById(adminname);
        if(admin!=null){
            return admin.getAdminpassword().equals(adminpassword);
        }

        return false;
    }

    @Override
    public boolean register(String adminname, String adminpassword) {
        Admin admin =adminMapper.selectById(adminname);
        if(admin==null){
            admin =new Admin();
            admin.setAdminname(adminname);
            admin.setAdminpassword(adminpassword);
            adminMapper.insert(admin);
            return true;
        }
        return false;
    }


    @Override
    public UserVO getUserById(String userId) {
        UserVO userVO = new UserVO();
        Account account = accountMapper.selectById(userId);
        Signon signon = signonMapper.selectById(userId);
        userVO.setUserId(account.getUserid());
        userVO.setFirstname(account.getFirstname());
        userVO.setLastname(account.getLastname());
        userVO.setPassword(signon.getPassword());
        userVO.setEmail(account.getEmail());
        userVO.setPhone(account.getPhone());
        userVO.setAddress1(account.getAddr1());
        userVO.setAddress2(account.getAddr2());
        userVO.setCity(account.getCity());
        userVO.setState(account.getState());
        userVO.setZip(account.getZip());
        userVO.setCountry(account.getCountry());
        return userVO;
    }

    @Override
    public UsersVO getUsers() {
        UsersVO usersVO = new UsersVO();
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "OK");
        QueryWrapper<Signon> queryWrapper_signon = new QueryWrapper<>();
        queryWrapper_signon.ne("username", "");
        List<Account> accountList = accountMapper.selectList(queryWrapper);
        List<Signon> signonList = signonMapper.selectList(queryWrapper_signon);
        List<UserVO> userList = new ArrayList<>();
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            Signon signon = signonList.get(i);
            UserVO userVO = new UserVO();
            userVO.setUserId(account.getUserid());
            userVO.setFirstname(account.getFirstname());
            userVO.setLastname(account.getLastname());
            userVO.setPassword(signon.getPassword());
            userVO.setEmail(account.getEmail());
            userVO.setPhone(account.getPhone());
            userVO.setAddress1(account.getAddr1());
            userVO.setAddress2(account.getAddr2());
            userVO.setCity(account.getCity());
            userVO.setState(account.getState());
            userVO.setZip(account.getZip());
            userVO.setCountry(account.getCountry());
            userList.add(userVO);
        }
        usersVO.setUserList(userList);
        return usersVO;
    }

    @Override
    public OrdersVO getOrders() {
        OrdersVO orders =new OrdersVO();
        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("userid","");
        List<Orders> ordersList=ordersMapper.selectList(queryWrapper);
        orders.setOrdersList(ordersList);
        return orders;
    }

    @Override
    public Orders getOrderById(String orderId) {
        Orders orders = ordersMapper.selectById(orderId);
        return orders;
    }

    @Override
    public void categoryEdit(CategoryVO categoryVO) {
        Category category=new Category();
        Category category1=categoryMapper.selectById(categoryVO.getCategoryId());
        category.setCategoryId(categoryVO.getCategoryId());
        category.setName(categoryVO.getCategoryName());
        category.setDescription(category1.getDescription());
        categoryMapper.updateById(category);
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public void updateUser(UserVO userVO) {

    }

    @Override
    public void createUser(UserVO userVO) {

    }
}
