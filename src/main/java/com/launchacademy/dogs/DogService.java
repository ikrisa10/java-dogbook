package com.launchacademy.dogs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DogService {

  private EntityManager em;

  public DogService(EntityManager em) {
    this.em = em;
  }

  public boolean save(Dog dog) {
    try {
      em.getTransaction().begin();
      em.persist(dog);
      em.getTransaction().commit();
      return true;
    } catch (Exception exception) {
      System.out.println(exception);
      em.getTransaction().rollback();
      return false;
    } finally {
      em.close();
    }
  }

  public List<Dog> findAll() {
    TypedQuery<Dog> query = em
        .createQuery("SELECT d FROM Dog d ORDER BY last_name, first_name", Dog.class);
    return query.getResultList();
  }

  public List<Dog> findByBreedId(Long id) {
    TypedQuery<Dog> query = em
        .createQuery("SELECT d FROM Dog d WHERE breed_id = :id ORDER BY last_name, first_name",
            Dog.class);
    query.setParameter("id", id);
    return query.getResultList();
  }


}
