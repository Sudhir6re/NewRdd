package com.mahait.gov.in.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mahait.gov.in.entity.ComponentMstEntity;
import com.mahait.gov.in.service.ComponentMstService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ComponentMstController extends BaseController{

	@Autowired
	ComponentMstService componentMstService;
	
	@PostMapping("/saveComponent")
	public ResponseEntity<List<ComponentMstEntity>> saveComponents(@RequestBody List<ComponentMstEntity> componentList) {
	    // Call the service to save the component list
	    List<ComponentMstEntity> savedComponents = componentMstService.saveComponet(componentList);

	    // Return the saved components with a CREATED status
	    return new ResponseEntity<>(savedComponents, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getAllComponent")
	public  List<ComponentMstEntity> getAllComponent(@ModelAttribute("componentMstEntity") ComponentMstEntity componentMstEntity,
			Model model,Locale locale,HttpSession session){
		model.addAttribute("lstAllComponent", componentMstService.lstAllComponent());
				return componentMstService.lstAllComponent();
		
			
	}

			}

