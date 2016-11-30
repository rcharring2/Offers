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

import edu.elon.lectures.web.dao.Message;
import edu.elon.lectures.web.dao.MessagesDao;
import edu.elon.lectures.web.dao.Offer;
import edu.elon.lectures.web.dao.OffersDAO;
import edu.elon.lectures.web.dao.User;
import edu.elon.lectures.web.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:edu/elon/lectures/web/config/dao-context.xml",
	"classpath:edu/elon/lectures/web/config/security-context.xml",
	"classpath:edu/elon/lectures/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTest {

  @Autowired
  private OffersDAO offersDao;
  
  @Autowired
  private DataSource dataSource;
  
  @Autowired
  private MessagesDao messagesDao;
  
  @Autowired
  private UsersDAO usersDao;
  
  private User user1 = new User("rharrington", "Ryan harrington", "hellothere", "rh@email.com", true, "ROLE_USER");
  private User user2 = new User("mtamblyn", "Madison Tamblyn", "hellothere", "mt@email.com", true, "ROLE_USER");

  @Before
  public void init() {
	JdbcTemplate jdbc = new JdbcTemplate(dataSource);
  
	jdbc.execute("delete from offers");
	jdbc.execute("delete from messages");
    jdbc.execute("delete from users");
  }
  
  @Test
  public void testSave(){
	usersDao.create(user1);
	usersDao.create(user2);

	Message message1 = new Message("Test subject 1", "Test content 1", "Issac Newton", "in@email.com", user1.getUsername());
	messagesDao.saveOrUpdate(message1);
  }
  
  
  
}
