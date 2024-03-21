package com.example.CloneGamestop.DTO;

import com.example.CloneGamestop.Model.Product;
import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private String description;
    private int price;

    //public ProductDTO() {
//
    //}

    public static ProductDTO fromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
}
