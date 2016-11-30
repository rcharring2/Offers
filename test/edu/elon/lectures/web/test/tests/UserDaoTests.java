package edu.elon.lectures.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.elon.lectures.web.dao.User;
import edu.elon.lectures.web.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:edu/elon/lectures/web/config/dao-context.xml",
	"classpath:edu/elon/lectures/web/config/security-context.xml",
	"classpath:edu/elon/lectures/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
  
  private User user1 = new User("rharrington", "Ryan harrington", "hellothere", "rh@email.com", true, "ROLE_USER");
  private User user2 = new User("mtamblyn", "Madison Tamblyn", "hellothere", "mt@email.com", true, "ROLE_USER");
  
  @Autowired
  private UsersDAO usersDao;
  @Autowired
  private DataSource datasource;
  
  @Before
  public void init() {
	JdbcTemplate jdbc = new JdbcTemplate(datasource);
  
	jdbc.execute("delete from offers");
	jdbc.execute("delete from messages");
    jdbc.execute("delete from users");
  }


  
  @Test
  public void testCreateRetrieve(){
	usersDao.create(user1);
	
	List<User> users1 = usersDao.getAllUsers();
	
	assertEquals("One user should have benn created and retirieved", 1, users1.size());
	
	assertEquals("Insertes user should match retrieved", user1, users1.get(0));
	
	usersDao.create(user2);
	
	List<User> users2 = usersDao.getAllUsers();
	
	assertEquals("Should be 2 retrieved users.", 2, users2.size());
	
	
  }
  
  @Test
  public void testExists(){
	usersDao.create(user1);
	usersDao.create(user2);
	

	assertTrue("User should exist.", usersDao.exists(user2.getUsername()));
	assertFalse("User should not exist.", usersDao.exists("jjjjj"));
	
  }
  
  
}
