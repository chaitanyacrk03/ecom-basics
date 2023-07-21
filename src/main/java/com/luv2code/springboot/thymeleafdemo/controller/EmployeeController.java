package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data

	public EmployeeService employeeService;
	public EmployeeController(EmployeeService empService){
		employeeService=empService;
	}
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> theEmployees=employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";

	}
	@GetMapping("/addButton")
	public String addbutton(Model theModel){
		Employee theEmployee= new Employee();
		theModel.addAttribute("employee",theEmployee);
		return "employees/employee-form";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee employee){
		employeeService.save(employee);
		return "redirect:/employees/list";
	}
	@GetMapping("/updateEmp")
	public String update(@RequestParam("employeeId") int theId, Model theModel){
		Employee theEmployee=employeeService.findById(theId);
		theModel.addAttribute("employee",theEmployee);
		return "employees/employee-form";

	}
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){
		employeeService.deleteById(theId);
		return "redirect:/employees/list";
	}

//	@PostConstruct
//	private void loadData() {
//
//		// create employees
//		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
//		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
//		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");
//
//		// create the list
//		theEmployees = new ArrayList<>();
//
//		// add to the list
//		theEmployees.add(emp1);
//		theEmployees.add(emp2);
//		theEmployees.add(emp3);
//	}
//
//	// add mapping for "/list"


}









