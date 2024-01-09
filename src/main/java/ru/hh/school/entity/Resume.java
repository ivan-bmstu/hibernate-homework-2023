package ru.hh.school.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;

//TODO: оформите entity
@Entity
@Table(name = "resume")
public class Resume {

  @Id
  @GeneratedValue(generator = "resume_id_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "resume_id_seq", allocationSize = 10)
  @Column(name = "id")
  private Integer id;

  @Column(name = "description")
  private String description;

  public Resume() {}

  public Resume(String description) {
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
