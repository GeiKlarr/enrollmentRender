package Enrollment.Enrollment.controller;

import Enrollment.Enrollment.dto.EnrolleeDto;
import Enrollment.Enrollment.models.Enrollee;
import Enrollment.Enrollment.models.UserEntity;
import Enrollment.Enrollment.security.SecurityUtil;
import Enrollment.Enrollment.service.EnrolleeService;
import Enrollment.Enrollment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class EnrolleeController {
    private EnrolleeService enrolleeService;
    private UserService userService;

    @Autowired
    public EnrolleeController(EnrolleeService enrolleeService,UserService userService) {
        this.userService = userService;
        this.enrolleeService = enrolleeService;
    }


/*
    1. add Getmapping for your URL
    2. Add model as parameter so that you can view it in your web
    3. get your functions in your service
    ex. List<EnrolleDto> enrolleeList = enrolleeService.findAllEnrolled

    */




    //create Kinder
    @GetMapping("/user/enroll/newKinder")
    public String enrolleeCreateForm(Model model){
        Enrollee enrollee = new Enrollee();
        model.addAttribute("enroll",enrollee);
        return "enrolled-create";
    }

    @PostMapping("/user/enroll/newKinder")
    public String saveEnrolleeKinder(@Valid @ModelAttribute("enroll")EnrolleeDto enrolleeDto, BindingResult result){
        if(result.hasErrors()){
            return "enrolled-create";
        }
    enrolleeService.saveEnrolleeKinder(enrolleeDto);
    return "redirect:/Home?success";
    }

    //Create Preparatory
    @GetMapping("/user/enroll/newPrep")
    public String createEnrolleePre(Model model){
        Enrollee enrollee = new Enrollee();
        model.addAttribute("enroll",enrollee);
        return "enrolled-createPrep";
    }

    @PostMapping("/user/enroll/newPrep")
    public String saveEnrolleePre(@Valid @ModelAttribute("enroll")EnrolleeDto enrolleeDto, BindingResult result){
        if(result.hasErrors()){
            return "enrolled-create";
        }
    enrolleeService.saveEnrolleePrep(enrolleeDto);
    return "redirect:/Home?success";
    }


    //Create Nursery
    @GetMapping("/user/enroll/newNursery")
    public String createFormNursery(Model model){
        Enrollee enrollee = new Enrollee();
        model.addAttribute("enroll",enrollee);
        return "enrolled-createNursery";
    }

    @PostMapping("/user/enroll/newNursery")
    public String saveEnrolleeNursery(@Valid @ModelAttribute("enroll")EnrolleeDto enrolleeDto, BindingResult result){
        if(result.hasErrors()){
            return "enrolled-create";
        }
        enrolleeService.saveEnrolleeNursery(enrolleeDto);
        return "redirect:/Home?success";
    }

    @GetMapping("/user/enroll/select")
    public String selectEnroll(Model model){
        Enrollee enrollee = new Enrollee();
        return "enrollment-select";
    }


    // delete
    @GetMapping("/enrolment/{enrolleeId}/delete")
    public String deleteEnrollee(@PathVariable("enrolleeId") long enrolleeId){
        enrolleeService.delete(enrolleeId);
        return "redirect:/Home";

    }


    @GetMapping("/user/status")
    public String chechStatus(Model model){
        UserEntity user = new UserEntity();
        List<EnrolleeDto> enrolleeDtoList = enrolleeService.findAllEnrolled();
        String username = SecurityUtil.getSessionUser();
        String isdsd = String.valueOf(userService.findByUsername(username));
        System.out.println(isdsd);
        if (username != null){
            user = userService.findByEmail(username);
            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("enroll",enrolleeDtoList);
        return "enrolled-status";
    }


}
