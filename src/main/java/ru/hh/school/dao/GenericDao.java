package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class GenericDao {
  private final SessionFactory sessionFactory;

  public GenericDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public <T> T get(Class<T> clazz, Serializable id) {
    return getSession().get(clazz, id);
  }

  public void save(Object object) {
    if (object == null) {
      return;
    }
    getSession().save(object);
  }

  public void merge(Object object) {
    if (object == null) {
      return;
    }
    getSession().merge(object);
  }

  public void update(Object object) {
    if (object == null) {
      return;
    }
    getSession().update(object);
  }

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }


}
