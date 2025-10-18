import dao.IDao;
import entities.Product;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.HibernateConfig;

public class Presentation2 {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class)) {
            @SuppressWarnings("unchecked")
            IDao<Product> productDao = context.getBean(IDao.class);

        // Create a product
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(100.0);

        productDao.create(product);
        System.out.println("Product saved: " + product.getName() + " (ID: " + product.getId() + ")");

        // Find the product by ID
        Product foundProduct = productDao.findById(product.getId());
        if (foundProduct != null) {
            System.out.println("Product found: " + foundProduct.getName() + " - $" + foundProduct.getPrice());
        }

        // List all products
        System.out.println("All products:");
            productDao.findAll().forEach(p -> 
                System.out.println("ID: " + p.getId() + ", Name: " + p.getName() + ", Price: $" + p.getPrice())
            );
        }
    }
}