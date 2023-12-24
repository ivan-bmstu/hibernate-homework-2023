package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Employer;

public class EmployerDao extends GenericDao {

  public EmployerDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public Employer getEager(int employerId) {
    return getSession()
            .createQuery("select employer from Employer employer join fetch employer.vacancies where employer.id = :employerId",
                    Employer.class)
            .setParameter("employerId", employerId)
            .getSingleResult();
  }
}
