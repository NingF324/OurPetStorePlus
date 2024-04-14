package org.ningf.ourpetstore.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.ningf.ourpetstore.entity.Category;
import org.ningf.ourpetstore.entity.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2024-03-28 22:17:23
* @Entity org.ningf.ourpetstore.entity.Orders
*/
@Repository
public interface OrdersMapper extends BaseMapper<Orders> {
    @Results({
            @Result(property = "orderId", column = "orderid"),
            @Result(property = "userId", column = "userid"),
            @Result(property = "orderDate", column = "orderdate"),
            @Result(property = "shipAddr1", column = "shipaddr1"),
            @Result(property = "shipAddr2", column = "shipaddr2"),
            @Result(property = "shipCity", column = "shipcity"),
            @Result(property = "shipState", column = "shipstate"),
            @Result(property = "shipZip", column = "shipzip"),
            @Result(property = "shipCountry", column = "shipcountry"),
            @Result(property = "billAddr1", column = "billaddr1"),
            @Result(property = "billAddr2", column = "billaddr2"),
            @Result(property = "billCity", column = "billcity"),
            @Result(property = "billZip", column = "billzip"),
            @Result(property = "billCountry", column = "billcountry"),
            @Result(property = "courier", column = "courier"),
            @Result(property = "totalPrice", column = "totalprice"),
            @Result(property = "billToFirstname", column = "billtofirstname"),
            @Result(property = "billToLastname", column = "billtolastname"),
            @Result(property = "shipToFirstname", column = "shiptofirstname"),
            @Result(property = "shipToLastname", column = "shiptolastname"),
            @Result(property = "creditCard", column = "creditcard"),
            @Result(property = "exprDate", column = "exprdate"),
            @Result(property = "cardType", column = "cardtype"),
            @Result(property = "locale", column = "locale")
    })
    @Select("SELECT * FROM orders")
    List<Orders> selectAllOrders();
}




