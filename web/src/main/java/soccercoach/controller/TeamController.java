package soccercoach.controller;

import com.faccaogames.soccercoach.model.Team;
import com.faccaogames.soccercoach.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/chooseTeam/{country}")
    private ModelAndView getTeams(@PathVariable("country") String country) {
        List<Team> teams = teamService.getTeamByCountry(country);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("chooseTeam");
        mav.addObject(teams);
        return mav;
    }
}