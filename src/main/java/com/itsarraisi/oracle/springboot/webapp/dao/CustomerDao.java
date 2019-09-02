package com.itsarraisi.oracle.springboot.webapp.dao;

import com.itsarraisi.oracle.springboot.webapp.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

@Repository
@Slf4j
public class CustomerDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Optional<Customer> findById(String id) {
        //language=MYSQL-SQL
        String query = "SELECT ID, NAME, SINCE FROM CUSTOMER WHERE ID = :id";
        MapSqlParameterSource mapParameter = new MapSqlParameterSource();
        mapParameter.addValue("id", id);
        try {
            Customer response = this.jdbcTemplate.queryForObject(query, mapParameter, new BeanPropertyRowMapper<>(Customer.class));
            return Optional.of(response);
        } catch (EmptyResultDataAccessException erdea) {
            if (log.isDebugEnabled())
                log.error("can't find id : {}", id, erdea);
            return Optional.empty();
        }
    }

    public List<Customer> findAll() {
        //language=MYSQL-SQL
        String query = "SELECT ID, NAME, SINCE FROM CUSTOMER";
        return this.jdbcTemplate.query(query, new HashMap<>(), new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                Customer value = new Customer();
                value.setId(resultSet.getString("ID"));
                value.setName(resultSet.getString("NAME"));
                value.setSince(resultSet.getString("SINCE"));
                return value;
            }
        });
    }

    public Customer save(Customer value) throws DataAccessException {
        value.setId(UUID.randomUUID().toString());
        value.setSince(new SimpleDateFormat("dd/MM/yyyy").format(Date.valueOf(LocalDate.now())));
        //language=MYSQL-SQL
        String query = "INSERT INTO CUSTOMER (ID, NAME, SINCE) VALUES(:id, :name, :since)";
        MapSqlParameterSource mapParameter = new MapSqlParameterSource();
        mapParameter.addValue("id", value.getId());
        mapParameter.addValue("name", value.getName());
        mapParameter.addValue("since", value.getSince());
        this.jdbcTemplate.update(query, mapParameter);
        return value;
    }

    public Customer update(Customer value) throws DataAccessException {
        //language=MYSQL-SQL
        String query = "UPDATE CUSTOMER SET NAME=:name, SINCE=:since WHERE ID=:id";
        MapSqlParameterSource mapParameter = new MapSqlParameterSource();
        mapParameter.addValue("id", value.getId());
        mapParameter.addValue("name", value.getName());
        mapParameter.addValue("since", value.getSince());
        this.jdbcTemplate.update(query, mapParameter);
        return value;
    }

    public boolean remove(Customer value) throws DataAccessException {
        //language=MYSQL-SQL
        String query = "DELETE FROM CUSTOMER WHERE Id=:id";
        MapSqlParameterSource mapParameter = new MapSqlParameterSource();
        mapParameter.addValue("id", value.getId());
        this.jdbcTemplate.update(query, mapParameter);
        return true;
    }

    public boolean removeById(String id) throws DataAccessException {
        //language=MYSQL-SQL
        String query = "DELETE FROM CUSTOMER WHERE Id=:id";
        MapSqlParameterSource mapParameter = new MapSqlParameterSource();
        mapParameter.addValue("id", id);
        this.jdbcTemplate.update(query, mapParameter);
        return false;
    }
}
