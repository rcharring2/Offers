package edu.elon.lectures.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDAO")
public class UsersDAO {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private SessionFactory sessionFactory;


  public Session session() {
	return sessionFactory.getCurrentSession();
  }

  @Transactional
  public void create(User user) {
	user.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
	session().save(user);
  }

  public boolean exists(String username) {
	User user = getUser(username);
	return user != null;
  }

  @SuppressWarnings("unchecked")
  public List<User> getAllUsers() {
	return session().createQuery("from User").list();
  }

  public User getUser(String username) {
	Criteria crit = session().createCriteria(User.class);
	crit.add(Restrictions.idEq(username));
	return (User) crit.uniqueResult();	
  }

}
