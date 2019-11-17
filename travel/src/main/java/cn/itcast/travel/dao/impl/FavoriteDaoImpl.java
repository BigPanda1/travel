package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.FavoriteId;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid =? and uid =?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        }catch (Exception e){

        }
        return favorite;
    }

    @Override
    public int FindCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid =?";
        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void addFavotite(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        String sql2 = "UPDATE tab_route SET COUNT = COUNT+1 WHERE rid =?";
        template.update(sql2,rid);
        template.update(sql,rid,new Date(),uid);
    }

    @Override
    public List<FavoriteId> findByuid(int uid) {
        String sql = "select * from tab_favorite where uid =?";
        return template.query(sql,new BeanPropertyRowMapper<FavoriteId>(FavoriteId.class),uid);
    }

    @Override
    public List<Route> findByRid(List<Integer> rids) {
        String sql = "SELECT * FROM tab_route WHERE rid IN";
        StringBuilder sb = new StringBuilder(sql);
        sb.append("(");
        for (int i = 0; i <rids.size() ; i++) {
            if (i == rids.size() -1){
                sb.append(rids.get(i));
            }else {
                sb.append(rids.get(i)+",");
            }
        }
        sb.append(")");
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    @Override
    public int findCountByRid(int uid) {
        String sql = "select count(*) from tab_favorite where uid =?";

        return template.queryForObject(sql,Integer.class,uid);
    }

    @Override
    public List<Route> findByPage(List<Integer> rids, int start, int pageSize) {
        String sql = "SELECT * FROM tab_route WHERE rid IN";
        StringBuilder sb = new StringBuilder(sql);
        sb.append("(");
        for (int i = 0; i <rids.size() ; i++) {
            if (i == rids.size() -1){
                sb.append(rids.get(i));
            }else {
                sb.append(rids.get(i)+",");
            }
        }
        sb.append(")");
        sb.append(" limit ?,?");
        sql = sb.toString();

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),start,pageSize);
    }

    @Override
    public int findRankCount(String routeName) {
        String sql = "SELECT COUNT(*) FROM tab_route WHERE COUNT >0";
        StringBuilder sb = new StringBuilder(sql);
        List param = new ArrayList();
        if (routeName !=null && routeName.length()>0 && !"null".equals(routeName)){
            sb.append(" and rname like ? ");
            param.add("%"+routeName+"%");
        }
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,param.toArray());
    }

    @Override
    public List<Route> findRankByPage(String routeName, int smallPrice, int bigPrice, int start, int pageSize) {
        String sql = "SELECT * FROM tab_route WHERE 1=1 AND COUNT >0 ";
        StringBuilder sb = new StringBuilder(sql);
        List param = new ArrayList();
        if (routeName !=null && routeName.length()>0 && !"null".equals(routeName)){
            sb.append(" and rname like ? ");
            param.add("%"+routeName+"%");
        }
        if (smallPrice != 0){
            sb.append(" and price >?");
            param.add(smallPrice);
        }
        if (bigPrice != 0){
            sb.append(" and price < ?");
            param.add(bigPrice);
        }

        sb.append(" ORDER BY COUNT DESC LIMIT ?,? ");
        sql = sb.toString();

        param.add(start);
        param.add(pageSize);

        List<Route> routeList = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), param.toArray());

        return routeList;
    }




}
