package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao cd = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        List<Category> list = null;
        if (categorys == null || categorys.size() == 0) {
//            System.out.println("数据库...");
            list = cd.findAll();
            for (int i = 0; i < list.size(); i++) {
                jedis.zadd("category", list.get(i).getCid(), list.get(i).getCname());
            }
            return list;
        } else {
//            System.out.println("redis...");
            list = new ArrayList<>();
            for (Tuple tuple : categorys) {
                Category c = new Category();
                c.setCid((int) tuple.getScore());
                c.setCname(tuple.getElement());
                list.add(c);
            }
            return list;
        }
    }
}
