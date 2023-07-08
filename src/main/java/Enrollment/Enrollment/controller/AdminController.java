package Enrollment.Enrollment.controller;


import Enrollment.Enrollment.dto.EnrolleeDto;
import Enrollment.Enrollment.models.SliderEntity;
import Enrollment.Enrollment.models.UserEntity;
import Enrollment.Enrollment.repository.SliderRepository;
import Enrollment.Enrollment.security.SecurityUtil;
import Enrollment.Enrollment.service.EnrolleeService;
import Enrollment.Enrollment.service.SliderService;
import Enrollment.Enrollment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private EnrolleeService enrolleeService;
    private UserService userService;
    private SliderService sliderService;
    private SliderRepository sliderRepository;

    @Autowired
    public AdminController(EnrolleeService enrolleeService,UserService userService, SliderService sliderService,SliderRepository sliderRepository) {
        this.enrolleeService = enrolleeService;
        this.userService =  userService;
        this.sliderService = sliderService;
        this.sliderRepository = sliderRepository;
    }

    //Read
    @GetMapping("/admin/enrolled")
    public String enrolleeList(Model model){
        List<EnrolleeDto> enrollee = enrolleeService.findAllEnrolled();
        model.addAttribute("enroll",enrollee);
        return "enrolled-list";
    }


    //update
    @GetMapping("/admin/enrolled/{enrolleeId}/edit")
    public String enrolleeUpdateForm(@PathVariable("enrolleeId") long enrolleeId, Model model){
        EnrolleeDto enrollee = enrolleeService.findByEnrolleeId(enrolleeId); //when getting a path variable you should get it to the Dto for security purp.
        List<String> optionsList = Arrays.asList("Male", "Female");
        List<String> optionsList2 = Arrays.asList("Pending", "Received");
        model.addAttribute("optionsList", optionsList);
        model.addAttribute("optionsList2", optionsList2);
        model.addAttribute("enrolleeList",enrollee);
        return "admin-update";
    }

    //update
    @PostMapping("/admin/enrolled/{enrolleeId}/edit")
    public String updateEnrollee(@PathVariable("enrolleeId") long enrolleeId, @ModelAttribute("enrolleeList") EnrolleeDto enrolleeDto, Model model){
        enrolleeDto.setId(enrolleeId);
        enrolleeDto.setEnrolledBy(enrolleeService.findByEnrolleeId(enrolleeId).getEnrolledBy());
        enrolleeService.updateEnrollee(enrolleeDto);
        return "redirect:/admin/dashboard";
    }

    // delete
    @GetMapping("/admin/enrolled/{enrolleeId}/delete")
    public String deleteEnrollee(@PathVariable("enrolleeId") long enrolleeId){
        enrolleeService.delete(enrolleeId);
        return "redirect:/admin/dashboard";

    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model){
        List<EnrolleeDto> enrolleeDtoList = enrolleeService.findAllEnrolled();
        model.addAttribute("enrolleeDtoList",enrolleeDtoList);
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        return "admin-dashboard";
    }


    @GetMapping("/admin/layoutmanager")
    public String layoutManagerForm(Model model){
        SliderEntity sliderEntity = new SliderEntity();
        model.addAttribute("sliderEntity", sliderEntity);
        List<SliderEntity> sliderList = sliderService.findAllPhoto();
        model.addAttribute("sliderList",sliderList);
        return "admin-addLayout";
    }

    @PostMapping("/admin/layoutmanager")
    public String addLayout(@ModelAttribute("enroll") SliderEntity sliderEntity, BindingResult bindingResult){
      sliderService.saveSlider(sliderEntity);
        return "redirect:/admin/layoutmanager";
    }


    //Update
    @GetMapping("/admin/layoutmanager/{sliderId}/edit")
    public String sliderForm(@PathVariable("sliderId") long sliderId, Model model){
        Optional<SliderEntity> slider = sliderRepository.findById(sliderId);
        List<SliderEntity> sliderList = sliderService.findAllPhoto();
        model.addAttribute("sliderList",sliderList);
        model.addAttribute("slider",slider);
        return "admin-editLayout";
    }

    @PostMapping("/admin/layoutmanager/{sliderId}/edit")
    public String editSlider(@PathVariable("sliderId") long sliderId,@ModelAttribute("slider") SliderEntity sliderEntity,Model model){
        sliderEntity.setId(sliderId);
        sliderService.updateSlider(sliderEntity);
        return "redirect:/admin/dashboard";
    }


}
