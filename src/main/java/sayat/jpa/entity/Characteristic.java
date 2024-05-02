package sayat.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_char")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_char_id")
    private Category category_char;

    @OneToMany(mappedBy = "characteristic")
    private List<CharProduct> charProductList;


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

    public Category getCategory_char() {
        return category_char;
    }

    public void setCategory_char(Category category_char) {
        this.category_char = category_char;
    }

    public List<CharProduct> getCharProductList() {
        return charProductList;
    }

    public void setCharProductList(List<CharProduct> charProductList) {
        this.charProductList = charProductList;
    }
}
