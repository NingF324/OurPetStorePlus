package org.ningf.ourpetstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ningf.ourpetstore.entity.*;
import org.ningf.ourpetstore.persistence.*;
import org.ningf.ourpetstore.service.HouTaiService;
import org.ningf.ourpetstore.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
    private ItemMapper itemMapper;

    @Autowired
    private OrderstatusMapper orderstatusMapper;

    @Autowired
    private LineitemMapper lineitemMapper;

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
        OrdersVO ordersVO =new OrdersVO();
        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("userid","");
        List<Orders> ordersList=ordersMapper.selectList(queryWrapper);
        ordersVO.setOrdersList(ordersList);
        return ordersVO;
    }

    @Override
    public Orders getOrderById(String orderId) {
        Orders orders = ordersMapper.selectById(orderId);
        return orders;
    }

    @Override
    public List<Orders> searchOrders(String keyword) {
        List<Orders> ordersList =new ArrayList<>();
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        try {
            int orderId = Integer.parseInt(keyword);
            queryWrapper.like("userid", keyword).or().like("orderid", orderId);
            // 执行搜索操作
        } catch (NumberFormatException e) {
            // 处理无法转换为整数的情况
            // 可以输出日志或者给出提示信息
        }
        ordersList = ordersMapper.selectList(queryWrapper);

        QueryWrapper<Lineitem> lineitemQueryWrapper = new QueryWrapper<>();
        lineitemQueryWrapper.like("itemid", keyword);
        List<Lineitem> lineitemList = lineitemMapper.selectList(lineitemQueryWrapper);

        for(Lineitem lineitem: lineitemList){
            Orders orders = ordersMapper.selectById(lineitem.getOrderid());
            if(!ordersList.contains(orders)){
                ordersList.add(orders);
            }
        }

        return ordersList;
    }
    @Override
    public boolean updateOrder(OrderVO ordersVO) {
        Orders orders = new Orders();
        orders.setOrderId(ordersVO.getOrderId());
        orders.setUserId(ordersVO.getUserid());
        orders.setOrderDate(ordersVO.getOrderDate());
        orders.setShipAddr1(ordersVO.getShipaddr1());
        orders.setShipAddr2(ordersVO.getShipaddr2());
        orders.setShipCity(ordersVO.getShipcity());
        orders.setShipState(ordersVO.getShipstate());
        orders.setShipZip(ordersVO.getShipzip());
        orders.setShipCountry(ordersVO.getShipcountry());
        orders.setBillAddr1(ordersVO.getBilladdr1());
        orders.setBillAddr2(ordersVO.getBilladdr2());
        orders.setBillCity(ordersVO.getBillcity());
        orders.setBillState(ordersVO.getBillstate());
        orders.setBillZip(ordersVO.getBillzip());
        orders.setBillCountry(ordersVO.getBillcountry());
        orders.setCourier(ordersVO.getCourier());
        orders.setTotalPrice(ordersVO.getTotalprice());
        orders.setBillToFirstname(ordersVO.getBilltofirstname());
        orders.setBillToLastname(ordersVO.getBilltolastname());
        orders.setShipToFirstname(ordersVO.getShiptofirstname());
        orders.setShipToLastname(ordersVO.getShiptolastname());
        orders.setCreditCard(ordersVO.getCreditcard());
        orders.setExprDate(ordersVO.getExprdate());
        orders.setCardType(ordersVO.getCardtype());
        orders.setLocale(ordersVO.getLocale());
        int updateResult = ordersMapper.updateById(orders);
        return updateResult > 0;

    }

    @Override
    public boolean deleteOrder(String orderId) {
        int deleteResult = ordersMapper.deleteById(orderId);
        return deleteResult>0;
    }

    @Override
    public boolean launchItem(String itemId) {
        Item item = itemMapper.selectById(itemId);
        String status = item.getStatus();
        boolean ans =!status.equals("A");
        item.setStatus("A");
        itemMapper.updateById(item);
        return ans;
    }

    @Override
    public boolean delistItem(String itemId) {
        Item item = itemMapper.selectById(itemId);
        String status = item.getStatus();
        boolean ans =!status.equals("P");
        item.setStatus("P");
        itemMapper.updateById(item);
        return ans;
    }

    @Override
    public Orderstatus getOrderStatusById(String orderId) {
        return orderstatusMapper.selectById(orderId);
    }

    @Override
    public boolean deleteUser(String userId) {
        int a= accountMapper.deleteById(userId);
        int b= signonMapper.deleteById(userId);
        return a>0&&b>0;
    }

    @Override
    public boolean updateUser(UserVO userVO) {
        // 验证参数
        if (userVO == null || userVO.getUserId() == null) {
            return false; // 或者抛出异常
        }

        // 构造要更新的 Account 对象
        Account account = new Account();
        account.setUserid(userVO.getUserId());
        account.setFirstname(userVO.getFirstname());
        account.setLastname(userVO.getLastname());
        account.setStatus(userVO.getStatus());
        account.setEmail(userVO.getEmail());
        account.setPhone(userVO.getPhone());
        account.setAddr1(userVO.getAddress1());
        account.setAddr2(userVO.getAddress2());
        account.setCity(userVO.getCity());
        account.setState(userVO.getState());
        account.setZip(userVO.getZip());
        account.setCountry(userVO.getCountry());

        // 构造要更新的 Signon 对象
        Signon signon = new Signon();
        signon.setUsername(userVO.getUserId());
        signon.setPassword(userVO.getPassword());

        // 执行更新操作
        int signonUpdateResult = signonMapper.updateById(signon);
        int accountUpdateResult = accountMapper.updateById(account);

        // 判断更新操作是否成功
        return signonUpdateResult > 0 && accountUpdateResult > 0;
    }

    @Override
    public boolean createUser(UserVO userVO) {
        Account account = new Account();
        Signon signon =new Signon();
        // 检查需要非空的属性是否为null
        if (userVO.getUserId() == "" || userVO.getPassword() == ""||userVO.getEmail()==""
        ||userVO.getFirstname()==""||userVO.getLastname()==""||userVO.getAddress1()==""
        ||userVO.getCity()==""||userVO.getState()==""||userVO.getZip()=="" ||userVO.getCountry()=="") {
            // 如果需要非空的属性为null，返回false，表示创建用户操作失败
            return false;
        }
        account.setUserid(userVO.getUserId());
        account.setFirstname(userVO.getFirstname());
        account.setLastname(userVO.getLastname());
        account.setStatus(userVO.getStatus());
        account.setEmail(userVO.getEmail());
        account.setPhone(userVO.getPhone());
        account.setAddr1(userVO.getAddress1());
        account.setAddr2(userVO.getAddress2());
        account.setCity(userVO.getCity());
        account.setState(userVO.getState());
        account.setZip(userVO.getZip());
        account.setCountry(userVO.getCountry());

        signon.setUsername(userVO.getUserId());
        signon.setPassword(userVO.getPassword());

        //执行插入操作
        int signonInsertResult = signonMapper.insert(signon);
        int accountInsertResult = accountMapper.insert(account);

        // 判断插入操作是否成功
        if (signonInsertResult > 0 && accountInsertResult > 0) {
            // 插入操作成功，返回true
            return true;
        } else {
            // 插入操作失败，返回false
            return false;
        }
    }
}
