package edu.elon.lectures.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import edu.elon.lectures.web.dao.Message;
import edu.elon.lectures.web.dao.MessagesDao;
import edu.elon.lectures.web.dao.Offer;
import edu.elon.lectures.web.dao.OffersDAO;
import edu.elon.lectures.web.dao.User;
import edu.elon.lectures.web.dao.UsersDAO;

@Service("usersService")
public class UsersService {

  @Autowired
  private UsersDAO usersDao;
  @Autowired
  private MessagesDao messagesDao;

  public void create(User user) {
	usersDao.create(user);

  }

  public boolean exists(String username) {

	return usersDao.exists(username);
  }

  @Secured("ROLE_ADMIN")
  public List<User> getAllUsers() {

	return usersDao.getAllUsers();
  }

  public void sendMessage(Message message){
	  messagesDao.saveOrUpdate(message);
	}
  
  public User getUser(String username){
	return usersDao.getUser(username);
  }

  public List<Message> getMessages(String username) {
	return messagesDao.getMessages(username);
  }

}
