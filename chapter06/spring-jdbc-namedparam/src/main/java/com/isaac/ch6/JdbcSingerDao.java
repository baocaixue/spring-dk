package com.isaac.ch6;

import com.isaac.ch6.dao.SingerDao;
import com.isaac.ch6.entries.Singer;
import lombok.Setter;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao{
    @Setter
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Singer> findAll() {
        return null;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findNameById(Long id) {
        String sql = "SELECT first_name ||' '|| last_name FROM singer WHERE id = :singerId";
        Map<String, Object> param = new HashMap<>();
        param.put("singerId", id);
        return jdbcTemplate.queryForObject(sql, param, String.class);
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {

    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long singerId) {

    }

    @Override
    public List<Singer> findAllWithAlbums() {
        return null;
    }

    @Override
    public void insertWithAlbum(Singer singer) {

    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null)
            throw  new BeanCreationException("JdbcSingerDao bean create exception : NamedParameterJdbcTemplate is null");
    }
}
