package com.isaac.ch6;

import com.isaac.ch6.entries.Singer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SelectSingerByFirstName extends MappingSqlQuery<Singer> {
    private static final String SELECT_SINGER_BY_FIRSTNAME = "select id, first_name, last_name, birth_date from singer where first_name = :first_name";

    public SelectSingerByFirstName(DataSource dataSource) {
        super(dataSource, SELECT_SINGER_BY_FIRSTNAME);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
    }

    @Override
    protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Singer singer = new Singer();
        singer.setId(rs.getLong("id"));
        singer.setLastName(rs.getString("last_name"));
        singer.setFirstName(rs.getString("first_name"));
        singer.setBirthDate(rs.getDate("birth_date"));
        return singer;
    }
}
