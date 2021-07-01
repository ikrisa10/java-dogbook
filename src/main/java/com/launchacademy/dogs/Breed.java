package com.launchacademy.dogs;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "breeds")
public class Breed {

  @Id
  @SequenceGenerator(name = "breeds_generator", sequenceName = "breeds_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breeds_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotBlank
  @Column(name = "breed", nullable = false, unique = true)
  private String breed;

  @OneToMany(mappedBy = "breed")
  private List<Dog> dogs = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public List<Dog> getDogs() {
    return dogs;
  }

  public void setDogs(List<Dog> dogs) {
    this.dogs = dogs;
  }
}
