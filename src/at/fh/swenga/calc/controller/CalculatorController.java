package at.fh.swenga.calc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalculatorController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/calc")
	public String calc(@RequestParam int number1, @RequestParam int number2, Model model) {
		model.addAttribute("result", number1 + number2);
		return "result";
	}
	
	@RequestMapping("/calcV2")
	public ModelAndView calcV2(@RequestParam(name="number1") int num1, @RequestParam(name="number2") int num2) {
		ModelAndView mav = new ModelAndView("result", "result", num1 + num2);
		return mav;
	}
 
	@RequestMapping("/calcV3")
	public ModelAndView calcV3(@RequestParam(name="number1") int num1, @RequestParam(name="number2") int num2) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", num1 + num2);
		mav.setViewName("result");
		return mav;
	}
 
	/**
	 * Old style still possible
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/calcV4")
	public ModelAndView calcV4(HttpServletRequest request) {
		String num1String = request.getParameter("number1");
		String num2String = request.getParameter("number2");
 
		int num1 = Integer.parseInt(num1String);
		int num2 = Integer.parseInt(num2String);
 
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", num1 + num2);
		mav.setViewName("result");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {
		return "showError";
	}
}
