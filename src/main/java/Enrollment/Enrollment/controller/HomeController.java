package Enrollment.Enrollment.controller;


import Enrollment.Enrollment.models.SliderEntity;
import Enrollment.Enrollment.repository.SliderRepository;
import Enrollment.Enrollment.service.SliderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    private SliderService sliderService;
    private SliderRepository sliderRepository;

    public HomeController(SliderService sliderService,SliderRepository sliderRepository) {
        this.sliderService = sliderService;
        this.sliderRepository = sliderRepository;
    }


    @GetMapping("/Home")
    public String homepage(Model model){
        Optional<SliderEntity> slider = sliderRepository.findById(1L);
        model.addAttribute("slider",slider);
        Optional<SliderEntity> slider2 = sliderRepository.findById(2L);
        model.addAttribute("slider2",slider2);
        Optional<SliderEntity> slider3 = sliderRepository.findById(3L);
        model.addAttribute("slider3",slider3);
        Optional<SliderEntity> slider4 = sliderRepository.findById(4L);
        model.addAttribute("slider4",slider4);
        Optional<SliderEntity> slider5 = sliderRepository.findById(5L);
        model.addAttribute("slider5",slider5);
        return "homepage";
    }

}
