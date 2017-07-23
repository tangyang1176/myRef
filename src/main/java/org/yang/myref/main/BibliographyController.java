package org.yang.myref.main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class BibliographyController {
	@Autowired
	DatabaseBibliographyRepository repository;
	
	@GetMapping
	public ModelAndView list() {
		Iterable<Bibliography> b = repository.findAll();
		return new ModelAndView("bibliographies/list", "bibliographies", b);
	}
	
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Bibliography b) {
		return new ModelAndView("bibliographies/view", "bibliography", b);
	}
	
	@GetMapping(params = "form")
	public String createForm(@ModelAttribute Bibliography b) {
		return "bibliographies/form";
	}
	
	@GetMapping(params = "search-term")
	public ModelAndView search(@RequestParam("search-term") String term) {
		Iterable<Bibliography> b = searchTerm(term);
		return new ModelAndView("bibliographies/list", "bibliographies", b);
	}
	
	@PostMapping
	public ModelAndView create(@Valid Bibliography bibliography, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("bibliographies/form", "formErrors", result.getAllErrors());
		}
		bibliography = repository.save(bibliography);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new bibliography!");
		return new ModelAndView("redirect:/{bibliography.id}", "bibliography.id", bibliography.getId());
	}
	
	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("An exception has occurred in Controller.");
	}
	
	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) { 
		repository.delete(id);
		Iterable<Bibliography> b = repository.findAll();
		return new ModelAndView("bibliographies/list", "bibliographies", b);
	}
	
	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Bibliography b) {
		return new ModelAndView("bibliographies/form", "bibliography", b);
	}
	
	// Utility Method
	private Iterable<Bibliography> searchTerm(String term) {		
		List<Bibliography> result = repository.findByAuthorIgnoreCaseContaining(term);
		result.addAll(repository.findByTitleIgnoreCaseContaining(term));
		result.addAll(repository.findByJournalIgnoreCaseContaining(term));
		result.addAll(repository.findByYearIgnoreCaseContaining(term));
		
		// Remove duplicate entries. 
		Set<Bibliography> set = new HashSet<>(result);
		result.clear();
		result.addAll(set);
		return result;
	}
}
