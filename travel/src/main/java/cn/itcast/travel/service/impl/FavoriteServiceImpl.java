package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.FavoriteId;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.FavoriteService;

import java.util.ArrayList;
import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao fd = new FavoriteDaoImpl();


    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = fd.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite != null;
    }

    @Override
    public void addFavorite(String rid, int uid) {

        fd.addFavotite(Integer.parseInt(rid),uid);
    }

    /**
     * 查询收藏的路线
     * @param uid
     * @return
     */
    @Override
    public List<Route> findByUid(int uid) {
        //查询这个用户收藏的所有rid
        List<FavoriteId> favoriteIds = fd.findByuid(uid);
        List<Integer> ridList = new ArrayList<>();
        for (FavoriteId favoriteId : favoriteIds) {
            Integer rid = favoriteId.getRid();
            ridList.add(rid);
        }

        List<Route> routes = fd.findByRid(ridList);

        return routes;
    }

    @Override
    public PageBean<Route> findCollPage(int currentPage, int pageSize,int uid) {
        PageBean<Route> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = fd.findCountByRid(uid);
        pb.setTotalCount(totalCount);

        int start =0;
        start = (currentPage-1) * pageSize;    //获取开始的索引

        int totalPage =0;
        totalPage = totalCount % pageSize ==0? totalCount / pageSize:totalCount/pageSize +1; //获取总页码
        pb.setTotalPage(totalPage);


        List<FavoriteId> favoriteIds = fd.findByuid(uid);
        List<Integer> ridList = new ArrayList<>();
        for (FavoriteId favoriteId : favoriteIds) {
            Integer rid = favoriteId.getRid();
            ridList.add(rid);
        }

        List<Route> routeList = fd.findByPage(ridList,start,pageSize);
        pb.setList(routeList);

        return pb;
    }

    @Override
    public PageBean<Route> findRank(String routeName, int smallPrice, int bigPrice, int currentPage, int pageSize) {

        PageBean<Route> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = fd.findRankCount(routeName);
        pb.setTotalCount(totalCount);

        int start =0;
        start = (currentPage-1) * pageSize;    //获取开始的索引

        int totalPage =0;
        totalPage = totalCount % pageSize ==0? totalCount / pageSize:totalCount/pageSize +1; //获取总页码
        pb.setTotalPage(totalPage);

        List<Route> routeList = fd.findRankByPage(routeName,smallPrice,bigPrice,start,pageSize);
        pb.setList(routeList);

        return pb;
    }




}
