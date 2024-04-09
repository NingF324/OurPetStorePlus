package org.ningf.ourpetstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ningf.ourpetstore.entity.Account;
import org.ningf.ourpetstore.entity.Signon;
import org.ningf.ourpetstore.persistence.AccountMapper;
import org.ningf.ourpetstore.persistence.SignonMapper;
import org.ningf.ourpetstore.service.HouTaiService;
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
    @Override
    public void login() {

    }

    @Override
    public void register() {

    }

    @Override
    public UserVO getUserById(String userId) {
        UserVO userVO = new UserVO();
        Account account = accountMapper.selectById(userId);
        Signon signon = signonMapper.selectById(userId);
        userVO.setUserId(account.getUserid());
        userVO.setUsername(signon.getUsername());
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
            userVO.setUsername(signon.getUsername());
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
    public void deleteUser(String userId) {

    }

    @Override
    public void updateUser(UserVO userVO) {

    }

    @Override
    public void createUser(UserVO userVO) {

    }
}
