package com.dil.parts.controller;

import com.dil.parts.model.Part;
import com.dil.parts.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PartController {

    private PartService partService;
    private static String typeSort = "all";
    private static int currentPage = 1;

    @Autowired
    @Qualifier(value = "partService")
    public void setPartService(PartService partService) {
        this.partService = partService;
    }

    @RequestMapping(value = "/parts/{typeSort}/{currentPage}", method = RequestMethod.GET)
    public String allParts(@PathVariable("typeSort") String typeSort,
                           @PathVariable("currentPage") Integer currentPage, Model model) {
        PartController.typeSort = typeSort;
        PartController.currentPage = currentPage;
        addAtributes(model, new Part());
        return "parts";
    }

    @RequestMapping(value = "/parts/add", method = RequestMethod.GET)
    public String allParts(Model model) {
        addAtributes(model, new Part());
        return "addPart";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUpdatePart(@ModelAttribute("part") Part part) {

        if (part.getId() == 0) {
            partService.addPart(part);
        }
        else {
            partService.updatePart(part);
        }

        return "redirect:/parts/" + typeSort + '/' + currentPage;

    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchPart(@ModelAttribute("part") Part part, Model model) {

        model.addAttribute("part", part);
        model.addAttribute("allParts", partService.getPartsByTitle(part.getTitle()));

        return "parts";

    }

    @RequestMapping(value = "/parts/remove/{id}")
    public String removePart(@PathVariable("id") int id) {

        partService.removePart(id);
        return "redirect:/parts/" + typeSort + '/' + currentPage;
    }

    @RequestMapping(value = "/parts/edit/{id}")
    public String editPart(@PathVariable("id") int id, Model model) {
        addAtributes(model, partService.getPartById(id));
        return "addPart";
    }

    private void addAtributes(Model model, Part part) {
        model.addAttribute("part", part);
        model.addAttribute("typeSort", PartController.typeSort);
        model.addAttribute("allParts", partService.getParts(PartController.typeSort, PartController.currentPage));
        model.addAttribute("currentPage", PartController.currentPage);
        model.addAttribute("maxAssembling", partService.getMaxAssembling());
        int numOfParts = partService.getNumOfParts(PartController.typeSort);
        model.addAttribute("numOfPages",
                (numOfParts % 10) != 0 ? numOfParts / 10 + 1 : numOfParts / 10);
    }

}
