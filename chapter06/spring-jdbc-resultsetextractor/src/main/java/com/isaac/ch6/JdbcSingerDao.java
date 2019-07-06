package com.isaac.ch6;

import com.isaac.ch6.dao.SingerDao;
import com.isaac.ch6.entries.Album;
import com.isaac.ch6.entries.Singer;
import lombok.Setter;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    @Setter
    private NamedParameterJdbcTemplate jdbcTemplate;

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
        return null;
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
        String sql = "select s.id , s.first_name, s.last_name, s.birth_date, " +
                "a.id album_id, a.title, a.release_date " +
                "from singer s left join album a on s.id = a.singer_id" ;
        return jdbcTemplate.query(sql, rs -> {
            Map<Long, Singer>  singerMap = new HashMap<>();
            Singer singer;
            while(rs.next()){
                Long id = rs.getLong("id");
                singer = singerMap.get(id);
                if(singer == null) {
                    singer = new Singer();
                    singer.setId(id);
                    singer.setFirstName(rs.getString("first_name"));
                    singer.setLastName(rs.getString("last_name"));
                    singer.setBirthDate(rs.getDate("birth_date"));
                    singerMap.put(id, singer);
                }
                long albumId = rs.getLong("album_id");
                if(albumId > 0) {
                    Album album = new Album();
                    album.setId(albumId);
                    album.setSingerId(id);
                    album.setTitle(rs.getString("title"));
                    album.setReleaseDate(rs.getDate("release_date"));
                    singer.addAlbum(album);
                }
            }
            return new ArrayList<>(singerMap.values());
        });
    }

    @Override
    public void insertWithAlbum(Singer singer) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(jdbcTemplate == null)
            throw new BeanCreationException("JdbcSingerDao bean create exception : jdbcTemplate is null ");
    }
}
