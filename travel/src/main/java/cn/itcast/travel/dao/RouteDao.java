package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     * 查询总记录数
     * @param cid
     * @return
     */
    int findTotalCount(int cid,String rname);

    /**
     * 查询当前页面的数据
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> findByPage(int cid,int start,int pageSize,String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    Route findOne(int rid);

    /**
     * 查询人气路线
     * @return
     */
    List<Route> findPopularityRoute();

    /**
     * 查询最新路线
     * @return
     */
    List<Route> findNewestRoute();

    /**
     * 查询主题路线
     * @return
     */
    List<Route> findThemeRoute();
}
