package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "total_description",length = 999999)
    private  String desc;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    @JsonIgnore
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    @JsonIgnore
    private Collection collection;

    @Column(name = "sold_count")
    private int soldCount;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    private Sales sales;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Stock> stocks;

    @Lob
    private byte[] imageData;
}