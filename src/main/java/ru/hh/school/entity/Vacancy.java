package ru.hh.school.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vacancy")
public class Vacancy {

  @Id
  @GeneratedValue(generator = "vacancy_id_sequence", strategy = GenerationType.IDENTITY)
  @Column(name = "vacancy_id")
  private Integer id;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id")
  private Employer employer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "area_id")
  private Area area;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "compensation_from")
  private Integer compensationFrom;

  @Column(name = "compensation_to")
  private Integer  compensationTo;

  @Column(name = "compensation_gross")
  private Boolean compensationGross;

  @Column(name = "creation_time")
  private LocalDateTime creationTime;

  @Column(name = "archiving_time")
  private LocalDateTime archivingTime;

  public Vacancy() {
  }

  public Vacancy(Employer employer) {
    this.employer = employer;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCompensationFrom(Integer compensationFrom) {
    this.compensationFrom = compensationFrom;
  }

  public void setCompensationTo(Integer compensationTo) {
    this.compensationTo = compensationTo;
  }

  public LocalDateTime getArchivingTime() {
    return archivingTime;
  }

  public void setArchivingTime(LocalDateTime archivingTime) {
    this.archivingTime = archivingTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vacancy vacancy = (Vacancy) o;
    EqualsBuilder eb = new EqualsBuilder();
    eb.append(title, vacancy.title);
    eb.append(creationTime, vacancy.creationTime);
    eb.append(description, vacancy.description); // не уверен, что это хорошая идея - большой объем текста
    return Objects.equals(id, vacancy.id);
  }

  @Override
  public int hashCode() {
    HashCodeBuilder hcb = new HashCodeBuilder();
    hcb.append(title)
            .append(creationTime)
            .append(description); // не уверен, что это хорошая идея - большой объем текста
    return hcb.toHashCode();
  }

}
