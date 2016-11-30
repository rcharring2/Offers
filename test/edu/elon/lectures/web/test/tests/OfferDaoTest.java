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

import edu.elon.lectures.web.dao.Offer;
import edu.elon.lectures.web.dao.OffersDAO;
import edu.elon.lectures.web.dao.User;
import edu.elon.lectures.web.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:edu/elon/lectures/web/config/dao-context.xml",
	"classpath:edu/elon/lectures/web/config/security-context.xml",
	"classpath:edu/elon/lectures/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTest {

  @Autowired
  private OffersDAO offersDao;
  
  @Autowired
  private DataSource dataSource;
  
  @Autowired
  private UsersDAO usersDao;
  
  private User user1 = new User("rharrington", "Ryan harrington", "hellothere", "rh@email.com", true, "ROLE_USER");
  private User user2 = new User("mtamblyn", "Madison Tamblyn", "hellothere", "mt@email.com", true, "ROLE_USER");
 
  private Offer offer1 = new Offer(user1, "This is an offer and it is good");
  private Offer offer2 = new Offer(user2, "This is an offer and it is great");
  private Offer offer3 = new Offer(user2, "This is an offer and it is not good");
  
  @Before
  public void init() {
	JdbcTemplate jdbc = new JdbcTemplate(dataSource);
  
	jdbc.execute("delete from offers");
	jdbc.execute("delete from messages");
    jdbc.execute("delete from users");
  }
  
  @Test
  public void testCreateRetrieve(){
	usersDao.create(user1);
	usersDao.create(user2);

	offersDao.saveOrUpdate(offer1);
	offersDao.saveOrUpdate(offer2);
	
	List<Offer> offers = offersDao.getOffers();
	assertEquals("should be 2 enabled user offers", 2, offers.size());
  }
  
  @Test
  public void testGetUsername(){
	usersDao.create(user1);
	usersDao.create(user2);

	offersDao.saveOrUpdate(offer1);
	offersDao.saveOrUpdate(offer2);
	offersDao.saveOrUpdate(offer3);
	
	List<Offer> offers1 = offersDao.getOffers(user2.getUsername());
	assertEquals("Should be 2 offers for this user", 2, offers1.size());
	
	List<Offer> offers2 = offersDao.getOffers("hehehe");
	assertEquals("Should be 0 offers for this user", 0, offers2.size());
  }
  
  @Test
  public void testUpdate(){
	usersDao.create(user1);
	usersDao.create(user2);
	offersDao.saveOrUpdate(offer1);
	offersDao.saveOrUpdate(offer2);
	offersDao.saveOrUpdate(offer3);
	
	offer3.setText("This offer has updated text");
	offersDao.saveOrUpdate(offer3);
	
	Offer retrieved = offersDao.getOffer(offer3.getId());
	assertEquals("Retrieved offer should be updated", offer3, retrieved);
	
  }
  
  @Test
  public void testDelete(){
	usersDao.create(user1);
	usersDao.create(user2);
	offersDao.saveOrUpdate(offer1);
	offersDao.saveOrUpdate(offer2);
	offersDao.saveOrUpdate(offer3);
	
	Offer retrieved1 = offersDao.getOffer(offer2.getId());
	assertNotNull("Offer with ID " + retrieved1.getId() + " should not be null", retrieved1);
	
	offersDao.delete(offer2.getId());

	Offer retrieved2 = offersDao.getOffer(offer2.getId());
	assertNull("Offer with ID " + retrieved1.getId() + " should be null", retrieved2);
	
  }
  
  @Test
  public void testGetById(){
	usersDao.create(user1);
	usersDao.create(user2);
	offersDao.saveOrUpdate(offer1);
	offersDao.saveOrUpdate(offer2);
	offersDao.saveOrUpdate(offer3);
	
	Offer retrieved1 = offersDao.getOffer(offer1.getId());
	assertEquals("Offers should match", offer1, retrieved1);
  }
  
}
