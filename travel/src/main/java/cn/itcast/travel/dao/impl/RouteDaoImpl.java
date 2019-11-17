package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid,String rname) {
//        String sql = "select count(*) from tab_route where cid =?";
//        Integer i = template.queryForObject(sql, Integer.class, cid);
//        return i;
        String sql = "select count(*) from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List parems = new ArrayList();
        if (cid != 0){
            sb.append(" and cid=? ");
            parems.add(cid);
        }

        if (rname != null && rname.length() >0 && !"null".equals(rname)){
            sb.append(" and rname like ? ");
            parems.add("%"+rname+"%");
        }

        sql = sb.toString();
        Integer integer = template.queryForObject(sql, Integer.class, parems.toArray());
//        System.out.println(integer);
        return integer;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
//        String sql = "select * from tab_route where cid =? limit ?,?";
//        List<Route> list = template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid, start, pageSize);
//        return list;
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List parems = new ArrayList();
        if (cid != 0){
            sb.append(" and cid=? ");
            parems.add(cid);
        }

        if (rname != null && rname.length() >0 && !"null".equals(rname)){
            sb.append(" and rname like ? ");
            parems.add("%"+rname+"%");
        }

        sb.append(" limit ? , ? ");
        sql = sb.toString();

        parems.add(start);
        parems.add(pageSize);

        List<Route> list = template.query(sql, new BeanPropertyRowMapper<>(Route.class), parems.toArray());
//        System.out.println(list);
        return list;
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid =?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }

    @Override
    public List<Route> findPopularityRoute() {
        String sql = "SELECT * FROM tab_route WHERE COUNT >0 ORDER BY COUNT DESC LIMIT ?,?";

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),0,4);
    }

    @Override
    public List<Route> findNewestRoute() {
        String sql = "SELECT * FROM tab_route ORDER BY rdate DESC LIMIT ?,?";

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),0,4);
    }

    @Override
    public List<Route> findThemeRoute() {
        String sql = "SELECT * FROM tab_route limit ?,?";

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),0,4);
    }
}
