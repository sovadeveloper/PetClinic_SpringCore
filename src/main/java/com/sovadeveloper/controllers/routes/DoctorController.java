package com.sovadeveloper.controllers.routes;

import com.sovadeveloper.entities.ClientEntity;
import com.sovadeveloper.services.DoctorService;
import com.sovadeveloper.services.NoteService;
import com.sovadeveloper.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DoctorController {
    private final DoctorService doctorService;
    private final NoteService noteService;
    private final PetService petService;

    @Autowired
    public DoctorController(DoctorService doctorService, NoteService noteService, PetService petService) {
        this.doctorService = doctorService;
        this.noteService = noteService;
        this.petService = petService;
    }

    @GetMapping("/doctor")
    public String doctorList(@AuthenticationPrincipal ClientEntity currentUser, Model model) throws Exception {
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("currentUser", currentUser);
        return "doctorList";
    }

    @GetMapping("/doctor/{id}")
    public String doctorProfile(@AuthenticationPrincipal ClientEntity currentUser,
                                @PathVariable Long id, Model model) throws Exception {
        model.addAttribute("doctor", doctorService.getById(id));
        model.addAttribute("notes", noteService.getAllByDoctor(doctorService.getById(id)));
        model.addAttribute("pets", petService.getAll());
        model.addAttribute("currentUser", currentUser);
        return "doctorProfile";
    }
}
