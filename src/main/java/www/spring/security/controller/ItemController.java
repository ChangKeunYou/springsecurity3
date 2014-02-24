package www.spring.security.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import www.spring.security.logic.HibernateItemService;
import www.spring.security.logic.Item;
import www.spring.security.logic.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value="/item" , method = {RequestMethod.GET,RequestMethod.POST})
public class ItemController {
    
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private HibernateItemService hibernateItemService;
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	
	@RequestMapping(value="index" , method = {RequestMethod.GET})
	public ModelAndView index() {
		// 상품목록정보 취득
		List<Item> itemList = this.itemService.getItemList();
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("itemList", itemList);
        //logger.info("dataResult=>" + itemList.toString());
		// 반환값이 되는 ModelAndView 인스턴스를 생성
		
		
		logger.info("@@@@@@@@@@@@@@@@@@=>" + this.hibernateItemService.getClass().getName());
		
		logger.info("#########################=>" + this.hibernateItemService.getItemList("findItemByID"));
		
		
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addAllObjects(model);
	logger.info("ItemController index 메서드 실행");
		return modelAndView;
	}
	
	
	@RequestMapping(value="edit" , method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView edit(@RequestParam("itemId")Integer itemId) {
		ModelAndView modelAndView = new ModelAndView("update");
		Item item = this.itemService.getItemByItemId(itemId);
		modelAndView.addObject(item);
		return modelAndView;
	}
	
	@RequestMapping(value="test" , method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView("test");
		return modelAndView;
	}
	
	
    
	@RequestMapping(value="search" , method = RequestMethod.POST)
	public ModelAndView search(String itemName) {
		if (itemName == null || itemName.equals("")) {
			// 검색상품명이 없는 경우, 상품 전체를 반환한다
			return this.index();
		}
        
		List<Item> itemList = this.itemService.getItemByItemName(itemName);
		if (itemList == null || itemList.isEmpty()) {
			// 검색상품명이 없는 경우, 상품 전체를 반환한다
			return this.index();
		}
        
		// 반환값이 되는 ModelAndView 인스턴스를 생성
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("itemList", itemList);
        
		return modelAndView;
	}
    
	@RequestMapping(value="create")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("add");
		modelAndView.addObject(new Item());
		return modelAndView;
	}
    
	@RequestMapping(value="register" , method = RequestMethod.POST)
	public ModelAndView register(@Valid Item item, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("add");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		this.itemService.entryItem(item);
		return this.index();
	}
    
	

	
	
    
	@RequestMapping(value="update" , method = RequestMethod.POST)
	public ModelAndView update(@Valid Item item, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("update");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		this.itemService.updateItem(item);
		return this.index();
	}
    
	@RequestMapping(value="confirm")
	public ModelAndView confirm(@RequestParam("itemId")Integer itemId) {
		ModelAndView modelAndView = new ModelAndView("delete");
		Item item = this.itemService.getItemByItemId(itemId);
		modelAndView.addObject(item);
		return modelAndView;
	}
    
	@RequestMapping(value="delete" ,method = RequestMethod.POST)
	public ModelAndView delete(Item item) {
		this.itemService.deleteItem(item);
		return this.index();
	}
    
	@RequestMapping(value="image")
	public void image(@RequestParam("itemId")Integer itemId, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		InputStream picture = null;
		OutputStream os = null;
		BufferedInputStream bis = null;
		try {
			picture = this.itemService.getPicture(itemId);
			os = response.getOutputStream();
			bis = new BufferedInputStream(picture);
			int data;
			while ((data = bis.read()) != -1) {
				os.write(data);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (picture != null) {
					picture.close();
					os.close();
					bis.close();
				}
			} catch (IOException e) {
				// close 안 되는 것뿐이므로 무시한다
			}
            
		}
	}
	

	
	
    
    
}
