package One_to_One.mapping_demo.One_to_One.mapping_demo.Controller;

import One_to_One.mapping_demo.One_to_One.mapping_demo.Entity.Product;
import One_to_One.mapping_demo.One_to_One.mapping_demo.Entity.ProductDetails;
import One_to_One.mapping_demo.One_to_One.mapping_demo.Repository.ProductDetailsRepository;
import One_to_One.mapping_demo.One_to_One.mapping_demo.Repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            Product updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ProductDetails> getProductDetails(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductDetails productDetails = product.get().getDetails();
            return ResponseEntity.ok(productDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/details")
    public ResponseEntity<ProductDetails> createProductDetails(@PathVariable Integer id, @Valid @RequestBody ProductDetails productDetails) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productDetails.setProduct(product.get());
            ProductDetails savedProductDetails = productDetailsRepository.save(productDetails);
            return new ResponseEntity<>(savedProductDetails, HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/details")
    public ResponseEntity<ProductDetails> updateProductDetails(@PathVariable Integer id, @Valid @RequestBody ProductDetails productDetails) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productDetails.setProduct(product.get());
            ProductDetails updatedProductDetails = productDetailsRepository.save(productDetails);
            return ResponseEntity.ok(updatedProductDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/details")
    public ResponseEntity<Void> deleteProductDetails(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductDetails productDetails = product.get().getDetails();
            productDetailsRepository.delete(productDetails);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}