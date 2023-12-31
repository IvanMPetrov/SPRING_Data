import entities.BasicIngredient;
import entities.BasicLabel;
import entities.BasicShampoo;
import entities.ProductionBatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("JPA_DB");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        ProductionBatch batch = new ProductionBatch(LocalDate.now());
        BasicLabel label = new BasicLabel("blue");
        BasicShampoo shampoo = new BasicShampoo("shower", label, batch);

        BasicIngredient ingredient = new BasicIngredient(100, "B12");
        BasicIngredient ingredient2 = new BasicIngredient(2, "Violet");

        shampoo.addIngredient(ingredient);
        shampoo.addIngredient(ingredient2);

        List<String> names = Arrays.asList("pam", "mitko", "velko");
        shampoo.setNames(names);

        entityManager.persist(ingredient);
        entityManager.persist(ingredient2);

        entityManager.persist(batch);
        entityManager.persist(label);
        entityManager.persist(shampoo);

        ProductionBatch productionBatch = entityManager.find(ProductionBatch.class, 1);

//        Set<BasicShampoo> shampoos = productionBatch.getShampoos();
//
//        shampoos.forEach(System.out::println);

        entityManager.getTransaction().commit();


    }
}

