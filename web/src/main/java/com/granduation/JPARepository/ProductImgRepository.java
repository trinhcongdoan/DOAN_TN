package com.granduation.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Product_Images;


@Repository
public interface ProductImgRepository extends JpaRepository<Product_Images, Integer> {

}
