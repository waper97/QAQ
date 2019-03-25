package com.yj.hqbz.controller.inventory;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yj.common.controller.BaseController;
import com.yj.hqbz.services.inventory.CheckStockService;

@RestController
@RequestMapping("/inventory/check")
public class CheckStockController extends BaseController{

	@Resource
	private CheckStockService checkStockService;
}
