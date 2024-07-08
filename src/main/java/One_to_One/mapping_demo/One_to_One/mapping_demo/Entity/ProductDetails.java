package One_to_One.mapping_demo.One_to_One.mapping_demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product_details")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    @Positive(message = "Height must be a positive number")

    private float height;
    @Positive(message = "Weight must be a positive number")
    private float weight;
    @Positive(message = "Length must be a positive number")

    private float length;
    @Positive(message = "Width must be a positive number")

    private float width;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonBackReference
    private Product product;


}
