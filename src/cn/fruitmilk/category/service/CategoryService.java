package cn.fruitmilk.category.service;

import java.util.List;

import cn.fruitmilk.category.dao.categoryDao;
import cn.fruitmilk.category.domain.Category;

public class CategoryService {
	private categoryDao cd = new categoryDao();
	
	public List<Category> findAll() {
		return cd.findByAll();
	}
}
