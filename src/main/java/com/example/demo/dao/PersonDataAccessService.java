package com.example.demo.dao;

import com.example.demo.module.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int insertPerson(UUID id, Person person) {
    return 0;
  }

  @Override
  public List<Person> selectAllPeople() {
    final String sql = "SELECT id, name FROM person";
    List<Person> people = jdbcTemplate.query(sql,(resultSet,i) -> {
      UUID id = UUID.fromString(resultSet.getString("id"));
      String name = resultSet.getString("name");
      return new Person(id, name);
    });
    return people;
  }

  @Override
  public Optional<Person> selectPersonById(UUID id) {
    final String sql = "SELECT id, name FROM person WHERE id = ?";
    Person p = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet,i) -> {
      UUID personId = UUID.fromString(resultSet.getString("id"));
      String name = resultSet.getString("name");
      return new Person(personId, name);
    });
    return Optional.ofNullable(p);
  }

  @Override
  public int deletePersonById(UUID id) {
    return 0;
  }

  @Override
  public int updatePersonById(UUID id, Person person) {
    return 0;
  }
}
