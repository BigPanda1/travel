package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface RouteSellerDao {

    /**
     * 根据sid查询商家信息
     * @param id
     * @return
     */
    Seller findBySid(int id);
}
