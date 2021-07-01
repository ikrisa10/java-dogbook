package com.launchacademy.dogs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BreedService {

  private EntityManager em;

  public BreedService(EntityManager em) {
    this.em = em;
  }

  public boolean save(Breed breed) {
    try {
      em.getTransaction().begin();
      em.persist(breed);
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

  public List<Breed> findByName(String breedName) {
    TypedQuery<Breed> query = em
        .createQuery("SELECT b FROM Breed b WHERE breed LIKE :breed", Breed.class);
    query.setParameter("breed", breedName);
    return query.getResultList();
  }
}
