package One_to_One.mapping_demo.One_to_One.mapping_demo.Repository;

import One_to_One.mapping_demo.One_to_One.mapping_demo.Entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Integer> {
}
