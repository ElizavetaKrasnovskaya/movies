package com.bsuir.spring.web;

import com.bsuir.spring.model.Employee;
import com.bsuir.spring.model.Movie;
import com.bsuir.spring.service.employee.EmployeeService;
import com.bsuir.spring.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilmsController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping("/showNewMovieForm")
    public String showNewMovieForm(Model model) {
        Movie movie = new Movie();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("movie", movie);
        model.addAttribute("employeesList", employees);
        return "new_movie";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(@ModelAttribute("movie") Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/";
    }

    @GetMapping("/showMovieFormForUpdateRating/{id}")
    public String showMovieFormForUpdateRating(@PathVariable(value = "id") int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "update_rating";
    }

    @PostMapping("/updateRating")
    public String updateRating(@ModelAttribute("movie") Movie movie) {
        System.out.println(movie.getId());
        Movie movie1 = movieService.getMovieById(movie.getId());
        double rating = (movie1.getRating() + movie.getRating()) / 2;
        movie.setRating(rating);
        movieService.saveMovie(movie);
        return "redirect:/";
    }

    @GetMapping("/showMovieFormForUpdate/{id}")
    public String showMovieFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("movie", movie);
        model.addAttribute("employeesList", employees);
        return "update_movie";
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable(value = "id") int id) {
        movieService.deleteMovieById(id);
        return "redirect:/";
    }

    @GetMapping("/pageMovie/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Movie> page = movieService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Movie> listMovies = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listMovies", listMovies);
        return "index";
    }
}