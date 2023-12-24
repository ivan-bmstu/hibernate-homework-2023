package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.employers.StatisticsDto;
import ru.hh.school.entity.Area;

public class VacancyDao extends GenericDao{
  public VacancyDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public StatisticsDto getSalaryStatistics(Area area){
    return getSession().createQuery(
            "SELECT new ru.hh.school.employers.StatisticsDto(" +
                    "count(v.id), min(v.compensationFrom), max(v.compensationTo))" +
            "FROM Vacancy v where v.area = :area", StatisticsDto.class)
        .setParameter("area", area)
        .getSingleResult();
  }
}
