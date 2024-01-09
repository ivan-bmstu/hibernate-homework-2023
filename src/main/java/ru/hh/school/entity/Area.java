package ru.hh.school.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "area")
public class Area {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "area_id")
  private Integer id;

  @Column(name = "name")
  private String name;

  public Area(){
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
