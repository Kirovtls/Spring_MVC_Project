package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Company;
import peaksoft.service.CompanyService;

@Controller
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/new")
    public String newCompany(Model model) {
        model.addAttribute("company", new Company());
        return "/company/newCompany";
    }

    @PostMapping("/save")
    public String createCompany(@ModelAttribute("company") Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies/getAllCompanies";
    }

    @GetMapping("/getAllCompanies")
    public String getCompanies(Model model) {
        model.addAttribute("companyLists", companyService.getAllCompanies());
        return "/company/getCompany";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "/company/editCompany";
    }

    @PatchMapping("/{id}")
    public String updateCompany(@ModelAttribute("company") Company company, @PathVariable Long id) {
        companyService.updateCompany(id,company);
        return "redirect:/companies/getAllCompanies";
    }

    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable("id") long id) {
        companyService.deleteCompanyById(id);
        return "redirect:/companies/getAllCompanies";
    }
}



