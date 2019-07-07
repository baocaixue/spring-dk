package com.isaac.ch6.dao;

import com.isaac.ch6.*;
import com.isaac.ch6.entries.Album;
import com.isaac.ch6.entries.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("singerDao")
public class JdbcSingerDao implements SingerDao{
    private static Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
    private DataSource dataSource;

    private SelectAllSingers selectAllSingers;
    private SelectSingerByFirstName selectSingerByFirstName;
    private StoredFunctionFirstNameById storedFunctionFirstNameById;
    private InsertSinger insertSinger;
    private UpdateSinger updateSinger;
    private DeleteSinger deleteSinger;
    private InsertSingerAlbum insertSingerAlbum;

    @Override
    public List<Singer> findAll() {
        return selectAllSingers.execute();
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        Map<String, Object> param = new HashMap<>();
        param.put("first_name", firstName);
        return selectSingerByFirstName.executeByNamedParam(param);
    }

    @Override
    public String findNameById(Long id) {
        throw new NotImplementedException("findNameById");
    }

    @Override
    public String findLastNameById(Long id) {
        throw new NotImplementedException("findLastNameById");
    }

    @Override
    public String findFirstNameById(Long id) {
        return storedFunctionFirstNameById.execute(id).get(0);
    }

    @Override
    public void insert(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void update(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        paramMap.put("id", singer.getId());
        updateSinger.updateByNamedParam(paramMap);
    }

    @Override
    public void delete(Long singerId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", singerId);
        deleteSinger.updateByNamedParam(paramMap);
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        return null;
    }

    @Override
    public void insertWithAlbum(Singer singer) {
        insertSingerAlbum = new InsertSingerAlbum(dataSource);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFirstName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("New singer inserted with id: " + singer.getId());
        List<Album> albums =
                singer.getAlbums();
        if (albums != null) {
            for (Album album : albums) {
                paramMap = new HashMap<>();
                paramMap.put("singer_id", singer.getId());
                paramMap.put("title", album.getTitle());
                paramMap.put("release_date", album.getReleaseDate());
                insertSingerAlbum.updateByNamedParam(paramMap);
            }
        }
        insertSingerAlbum.flush();
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        selectAllSingers = new SelectAllSingers(dataSource);
        selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
        storedFunctionFirstNameById = new StoredFunctionFirstNameById(dataSource);
        insertSinger = new InsertSinger(dataSource);
        updateSinger = new UpdateSinger(dataSource);
        deleteSinger = new DeleteSinger(dataSource);
    }
}
