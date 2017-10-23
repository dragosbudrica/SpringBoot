package com.kepler.rominfo.dao;

import com.kepler.rominfo.domain.vo.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {
     List<Category> getAllCategories();
     Category getCategoryByName(@Param("categoryName") String categoryName);
}
