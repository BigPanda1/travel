package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.RouteSellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.RouteSellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao rd = new RouteDaoImpl();
    private RouteImgDao ri = new RouteImgDaoImpl();
    private RouteSellerDao rs = new RouteSellerDaoImpl();
    private FavoriteDao fd = new FavoriteDaoImpl();


    /**
     * 分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public PageBean<Route> queryPage(int cid, int currentPage, int pageSize,String rname) {
        PageBean<Route> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);  //当前页码
        pb.setPageSize(pageSize);        //每页显示条数

        int totalCount = rd.findTotalCount(cid,rname);  //总记录数
        pb.setTotalCount(totalCount);

        int start =0;
        start = (currentPage-1) * pageSize;    //获取开始的索引

        int totalPage =0;
        totalPage = totalCount % pageSize ==0? totalCount / pageSize:totalCount/pageSize +1; //获取总页码
        pb.setTotalPage(totalPage);

        List<Route> list = null;
        list = rd.findByPage(cid,start,pageSize,rname);    //获取每页显示的数据
        pb.setList(list);

        return pb;
    }

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {

        Route route = rd.findOne(Integer.parseInt(rid));

        List<RouteImg> list = ri.findByid(route.getRid());
        route.setRouteImgList(list);

        Seller seller = rs.findBySid(route.getSid());
        route.setSeller(seller);

        return route;
    }

    /**
     * 收藏次数
     * @param rid
     * @return
     */
    @Override
    public int findCountByRid(String rid) {

        return fd.FindCountByRid(Integer.parseInt(rid));
    }

    @Override
    public List<Route> findPopularityRoute() {

        return rd.findPopularityRoute();
    }

    /**
     * 查询最新路线
     * @return
     */
    @Override
    public List<Route> findNewestRoute() {
        return rd.findNewestRoute();
    }

    /**
     * 查询主题线路
     * @return
     */
    @Override
    public List<Route> findThemeRoute() {
        return rd.findThemeRoute();
    }
}
