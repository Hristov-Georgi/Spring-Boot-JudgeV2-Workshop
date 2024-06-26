package workshopJudge_v2.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.extras.springsecurity6.auth.Authorization;
import workshopJudge_v2.service.CommentService;
import workshopJudge_v2.service.ExerciseService;
import workshopJudge_v2.service.UserEntityService;

import java.security.Principal;

@Controller
public class HomeController {

    private final ExerciseService exerciseService;
    private final CommentService commentService;
    private final UserEntityService userService;


    public HomeController(ExerciseService exerciseService, CommentService commentService, UserEntityService userService) {
        this.exerciseService = exerciseService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute("exercises", this.exerciseService.getExercisesNames());

        model.addAttribute("topStudents", this.userService.findTopStudents());

        model.addAttribute("averageScore", this.commentService.findAverageScore());

        model.addAttribute("usersCount", this.userService.findUsersCount());

        model.addAttribute("scoresMap", this.commentService.findScoreMap());

        return "home";

    }

    @GetMapping("/")
    public String index(Principal principal) {

        if(principal != null) {
            return "redirect:home";
        }

        return "index";
    }



}

