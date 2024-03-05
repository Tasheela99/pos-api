package com.pos.posapi.service;

import com.pos.posapi.dto.CategoryDto;
import com.pos.posapi.dto.requestdto.RequestCategorySaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;


import java.util.List;

public interface CategoryService {
    CommonResponseDTO createCategory(RequestCategorySaveDto categoryDto);

    CommonResponseDTO updateCategory(int id, RequestCategorySaveDto categoryDto);

    CommonResponseDTO deleteCategory(int id);

    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(int id);
}
