package org.ningf.ourpetstore.vo;

import lombok.Data;

/**
 * @description:
 * @author: Lenovo
 * @time: 2024/3/28 22:39
 */
@Data
public class UserVO {
    private String userId;
    private String firstname;
    private String lastname;
    private String password;
    private String status;
    private String email;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;

}
