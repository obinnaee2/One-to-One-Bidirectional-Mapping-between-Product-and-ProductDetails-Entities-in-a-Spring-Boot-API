package One_to_One.mapping_demo.One_to_One.mapping_demo.Repository;

import One_to_One.mapping_demo.One_to_One.mapping_demo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
