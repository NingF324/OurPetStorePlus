package org.ningf.ourpetstore.service;

import org.ningf.ourpetstore.vo.UserVO;
import org.ningf.ourpetstore.vo.UsersVO;

public interface HouTaiService {
    public void login();
    public void register();
    public UserVO getUserById(String userId);

    public UsersVO getUsers();
}