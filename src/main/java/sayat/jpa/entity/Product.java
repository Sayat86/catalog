package sayat.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product_c")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_c")
    private String name;

    @Column(name = "price_c")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_c_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<CharProduct> charProducts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CharProduct> getCharProducts() {
        return charProducts;
    }

    public void setCharProducts(List<CharProduct> charProducts) {
        this.charProducts = charProducts;
    }
}
