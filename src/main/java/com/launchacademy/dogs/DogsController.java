package com.launchacademy.dogs;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@WebServlet(urlPatterns = {"/dogs/new", "/dogs"})
public class DogsController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String breedParam = req.getParameter("breed");
    if (breedParam != null && breedParam != "") {
      BreedService breedService = new BreedService(getEmf().createEntityManager());
      List<Breed> foundBreed = breedService.findByName(breedParam);
      if (foundBreed.size() > 0) {
        Long id = foundBreed.get(0).getId();
        DogService dogService = new DogService(getEmf().createEntityManager());
        List<Dog> foundDogs = dogService.findByBreedId(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/dogs/index.jsp");
        req.setAttribute("dogs", foundDogs);
        req.setAttribute("firstName", req.getSession().getAttribute("firstName"));
        dispatcher.forward(req, resp);
      } else {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
      }

    } else if (req.getServletPath().equals("/dogs/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/dogs/new.jsp");
      dispatcher.forward(req, resp);
    } else if (req.getServletPath().equals("/dogs")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/dogs/index.jsp");
      DogService dogService = new DogService(getEmf().createEntityManager());
      List<Dog> dogs = dogService.findAll();
      req.setAttribute("dogs", dogs);
      req.setAttribute("firstName", req.getSession().getAttribute("firstName"));
      dispatcher.forward(req, resp);
    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getServletPath().equals("/dogs")) {
      Dog dog = new Dog();
      dog.setFirstName(req.getParameter("firstName"));
      dog.setLastName(req.getParameter("lastName"));
      dog.setPhotoUrl(req.getParameter("photoUrl"));
      dog.setSex(req.getParameter("sex"));

      BreedService breedService = new BreedService(getEmf().createEntityManager());
      List<Breed> savedBreed = breedService.findByName(req.getParameter("breed"));
      if (savedBreed.size() > 0) {
        dog.setBreed(savedBreed.get(0));
      } else {
        Breed breed = new Breed();
        breed.setBreed(req.getParameter("breed"));

        if (breedService.save(breed)) {
          dog.setBreed(breed);
        } else {
          System.out.println("Breed wasn't saved.");
        }
      }

      DogService dogService = new DogService(getEmf().createEntityManager());
      Set<ConstraintViolation<Dog>> violationsDog = validation(dog);
      if (violationsDog.size() > 0) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/errors/error.jsp");
        req.setAttribute("errors", violationsDog);
        dispatcher.forward(req, resp);
      } else {
        if (!dogService.save(dog)) {
          System.out.println("Dog wasn't saved");
        }

        HttpSession session = req.getSession();
        session.setAttribute("firstName", dog.getFirstName());

        resp.sendRedirect("/dogs");
      }
    } else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  private EntityManagerFactory getEmf() {
    return (EntityManagerFactory) this.getServletContext().getAttribute("emf");
  }

  private Set validation(Dog dog) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    return validator.validate(dog);
  }

  private Set validationBreed(Breed breed) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    return validator.validate(breed);
  }

}
