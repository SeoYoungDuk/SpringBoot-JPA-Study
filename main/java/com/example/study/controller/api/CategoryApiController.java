package com.example.study.controller.api;

import ch.qos.logback.classic.Logger;
import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Category;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.CategoryApiRequest;
import com.example.study.model.network.response.CategoryApiResponse;
import com.example.study.service.CategoryApiLogicService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {
//    @Autowired
//    private CategoryApiLogicService categoryApiLogicService;
//
//    @PostConstruct
//    public void init(){
//        this.baseService = categoryApiLogicService;
//    }

//    @Override
//    @PostMapping("")
//    public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> request) {
//        return categoryApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")
//    public Header<CategoryApiResponse> read(@PathVariable Long id) {
//
//        return categoryApiLogicService.read(id);
//
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> request) {
//
//        return categoryApiLogicService.update(request);
//    }
//
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header delete(@PathVariable Long id) {
//        return categoryApiLogicService.delete(id);
//    }
}
