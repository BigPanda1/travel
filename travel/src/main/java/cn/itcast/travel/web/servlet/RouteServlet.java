package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService rs = new RouteServiceImpl();
    private FavoriteService fs = new FavoriteServiceImpl();

    /**
     * 分页查询功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void queryPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String rname = request.getParameter("rname");

        if (rname !=null && rname.length() >0 ){

        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        }

//        System.out.println(rname);

        int cid =0;
        if (cidStr != null && cidStr.length() >0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }

        int currentPage =0;
        if (currentPageStr != null && currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage =1;
        }

        int pageSize =0;
        if (pageSizeStr != null && pageSizeStr.length() >0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }

        PageBean<Route> pb = rs.queryPage(cid, currentPage, pageSize,rname);
        writeValue(pb,response);

    }

    /**
     * 根据rid查询线路详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rid = request.getParameter("rid");
        Route route = rs.findOne(rid);

        int count = rs.findCountByRid(rid);
        route.setCount(count);

        writeValue(route,response);

    }

    /**
     * 是否收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rid = request.getParameter("rid");

        User user = (User) request.getSession().getAttribute("user");
        int uid;

        if (user == null){
            uid = 0;
        }else {
            uid = user.getUid();
        }

        boolean flag = fs.isFavorite(rid, uid);
        writeValue(flag,response);

    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            return;
        }else {
            uid = user.getUid();
        }

        fs.addFavorite(rid,uid);
    }

    /**
     * 查询收藏路线
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void queryPageCollection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            return;
        }else {
            uid = user.getUid();
        }

        List<Route> routes = fs.findByUid(uid);
        writeValue(routes,response);

    }

    /**
     * 分页查询收藏路线
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void queryPageCollection2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            return;
        }else {
            uid = user.getUid();
        }

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage =0;
        if (currentPageStr != null && currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage =1;
        }

        int pageSize =0;
        if (pageSizeStr != null && pageSizeStr.length() >0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 4;
        }

        PageBean<Route> pb = fs.findCollPage(currentPage, pageSize, uid);
        writeValue(pb,response);

    }

    /**
     * 收藏排行榜
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void rankList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String routeName = request.getParameter("routeName");
        String smallPriceStr = request.getParameter("smallPrice");
        String bigPriceStr = request.getParameter("bigPrice");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        if (routeName !=null && routeName.length() >0 && !"null".equals(routeName) ){

            routeName = new String(routeName.getBytes("iso-8859-1"),"utf-8");
        }

        int smallPrice = 0;
        if (smallPriceStr != null && smallPriceStr.length() >0 && !"null".equals(smallPriceStr)){
            smallPrice = Integer.parseInt(smallPriceStr);
        }

        int bigPrice =0;
        if (bigPriceStr != null && bigPriceStr.length() >0 && !"null".equals(bigPriceStr)){
            bigPrice = Integer.parseInt(bigPriceStr);
        }

        int currentPage =0;
        if (currentPageStr != null && currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage =1;
        }

        int pageSize =0;
        if (pageSizeStr != null && pageSizeStr.length() >0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 8;
        }

        PageBean<Route> pb = fs.findRank(routeName,smallPrice,bigPrice,currentPage,pageSize);

        writeValue(pb,response);

    }

    /**
     * 查询人气路线
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void popularity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Route> routeList = rs.findPopularityRoute();
        writeValue(routeList,response);

    }

    /**
     * 查询最新旅游
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void newest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Route> routeList = rs.findNewestRoute();
        writeValue(routeList,response);

    }

    /**
     * 查询主题旅游
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void theme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Route> routeList = rs.findThemeRoute();
        writeValue(routeList,response);
    }


}
