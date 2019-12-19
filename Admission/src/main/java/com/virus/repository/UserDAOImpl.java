package com.virus.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.virus.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	private EntityManager entityManager;

	@Autowired
	public UserDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public List<User> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User", User.class);
		List<User> user = query.getResultList();
		return user;
	}
	@Override
	public User findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		User user = session.get(User.class, id);
		return user;
	}
	@Override
	public void save(User user) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);
	}
	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from User where id=:userId");
		query.setParameter("userId", id);
	}
}
