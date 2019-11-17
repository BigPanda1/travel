package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteService {

    /**
     * 判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavorite(String rid,int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void addFavorite(String rid, int uid);


    List<Route> findByUid(int uid);

    /**
     * 分页查询收藏页面
     * @param
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Route> findCollPage(int currentPage,int pageSize,int uid);


    /**
     * 排行榜分页查询
     * @param routeName
     * @param smallPrice
     * @param bigPrice
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Route> findRank(String routeName, int smallPrice, int bigPrice, int currentPage, int pageSize);


}
