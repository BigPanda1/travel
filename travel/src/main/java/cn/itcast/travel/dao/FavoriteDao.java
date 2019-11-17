package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.FavoriteId;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteDao {

    /**
     * 根据rid和uid查询favorite对象
     * @param rid
     * @param uid
     * @return
     */
    Favorite findByRidAndUid(int rid,int uid);

    int FindCountByRid(int rid);

    /**
     * 增加收藏
     * @param rid
     * @param uid
     */
    void addFavotite(int rid,int uid);


    /**
     * 根据uid查询favorite表数据封装对象
     * @param uid
     * @return
     */
    List<FavoriteId> findByuid(int uid);

    /**
     * 根据rid集合查询用户收藏的所有线路集合
     * @param rids
     * @return
     */
    List<Route> findByRid(List<Integer> rids);

    /**
     * 根据uid查询用户收藏线路的总数量
     * @param uid
     * @return
     */
    int findCountByRid(int uid);

    /**
     * 分页查询用户收藏
     * @param ridList
     * @param start
     * @param pageSize
     * @return
     */
    List<Route> findByPage(List<Integer> ridList, int start, int pageSize);

    /**
     * 查询被收藏的路线总数量
     * @return
     */
    int findRankCount(String routeName);

    /**
     * 查询排行榜的线路信息
     * @param routeName
     * @param smallPrice
     * @param bigPrice
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Route> findRankByPage(String routeName, int smallPrice, int bigPrice, int currentPage, int pageSize);


}
