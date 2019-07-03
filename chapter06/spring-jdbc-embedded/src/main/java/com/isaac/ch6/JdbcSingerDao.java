package com.isaac.ch6;

import com.isaac.ch6.dao.SingerDao;
import com.isaac.ch6.entries.Singer;
import lombok.Data;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Data
public class JdbcSingerDao implements SingerDao, InitializingBean{
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Singer> findAll() {
        return null;
    }

    @Override
    public List<Singer> findByFirstName() {
        return null;
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT first_name || ' ' || last_name FROM singer WHERE id = ?", new Object[]{id}, String.class);
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

    @Override
    public void afterPropertiesSet() throws Exception {
        if(jdbcTemplate == null)
            throw new BeanCreationException("ERROR, JdbcTemplate is Null ");
    }
}
