package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteService {

    /**
     * 分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    PageBean<Route> queryPage(int cid,int currentPage,int pageSize,String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    Route findOne(String rid);

    /**
     * 查询线路收藏次数
     * @param rid
     * @return
     */
    int findCountByRid(String rid);

    /**
     * 查询人气线路
     * @return
     */
    List<Route> findPopularityRoute();

    /**
     * 查询最新路线
     * @return
     */
    List<Route> findNewestRoute();

    /**
     * 查询主题线路
     * @return
     */
    List<Route> findThemeRoute();
}
