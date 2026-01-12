package com.menace.sanriva.organisation.dao;

import com.menace.sanriva.organisation.Organisation;
import jakarta.annotation.Nullable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class OrganisationDao {

    private final NamedParameterJdbcTemplate jdbc;
    private final OrganisationRowMapper organisationRowMapper;

    public OrganisationDao(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.organisationRowMapper = new OrganisationRowMapper();
    }

    public @Nullable Organisation findBySorCode(long sorCode) {
        String sql = """
            SELECT 
                name,
                type,
                region,
                specialization,
                cvr,
                sor_code,
                sor_code_parent
            FROM organisations 
            WHERE sor_code = :sor_code
            """;

        var params = new MapSqlParameterSource();
        params.addValue("sor_code", sorCode);
        try {
            return jdbc.queryForObject(sql, params, organisationRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class OrganisationRowMapper implements RowMapper<Organisation> {

        @Override
        public Organisation mapRow(ResultSet rs, int rowNum) throws java.sql.SQLException {
            return new Organisation(
                rs.getString("name"),
                rs.getString("type"),
                rs.getString("region"),
                rs.getString("specialization"),
                rs.getLong("cvr"),
                rs.getLong("sor_code"),
                rs.getLong("sor_code_parent") == 0 ? null : rs.getLong("sor_code_parent")
            );
        }

    }

}
