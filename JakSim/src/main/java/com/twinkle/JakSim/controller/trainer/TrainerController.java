package com.twinkle.JakSim.controller.trainer;

import com.twinkle.JakSim.model.dto.timetable.response.TimetableDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.*;
import com.twinkle.JakSim.model.service.review.ReviewService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class TrainerController {
    @Autowired
    private TrainerService trainerService;
    @Autowired
    ReviewService reviewService;

    @GetMapping("/trainerRegister")
    public String trainerSignUp(Model model, @AuthenticationPrincipal User info) {
        model.addAttribute("head_title", "트레이너 등록");
        model.addAttribute("userId", info);

        return "content/trainer/trainerRegister";
    }

    @PostMapping("/trainerRegister")
    public String trainerSignUp(TrainerInsertDto trainerDto, @AuthenticationPrincipal User info, Model model) {
        model.addAttribute("head_title", "트레이너 등록");
        System.out.println(trainerDto.toString());
        trainerService.TrainerSignUp(trainerDto, info.getUsername());

        return "content/trainer/trainerRegister";
    }

    @GetMapping("/trainerUpdate/{trainerId}")
    public String trainerUpdate(Model model, @PathVariable("trainerId") int tIdx, @AuthenticationPrincipal User info) {
        model.addAttribute("head_title", "트레이너 정보수정");
        model.addAttribute("userId", info);
        model.addAttribute("trainer", trainerService.searchTrainer(tIdx));

        return "content/trainer/trainerUpdate";
    }

    @PostMapping("/trainerUpdate")
    public String trainerUpdate(TrainerInsertDto trainerDto, @AuthenticationPrincipal User info, Model model) {
        //String userId = info.getUsername();
        model.addAttribute("head_title", "트레이너 정보수정");
        System.out.println(trainerDto.toString());
        trainerService.updateTrainer(trainerDto, info.getUsername());

        return "content/trainer/trainerUpdate";
    }
    @PostMapping("/trainerDelete")
    public String trainerDelete(TrainerInsertDto trainerDto, @AuthenticationPrincipal User info, Model model) {
        //String userId = info.getUsername();

        model.addAttribute("head_title", "트레이너 정보수정");
        System.out.println(trainerDto.toString());
        trainerService.deleteTrainer(info.getUsername());

        return "content/index";
    }

    @GetMapping("/trainer/{trainerId}")
    public String viewTrainer(@PathVariable("trainerId") int tIdx, @AuthenticationPrincipal User info, Model model) throws SQLException {
        //String userId = info.getUsername();
        //model.addAttribute("user", userId);

        model.addAttribute("head_title", "트레이너 상세페이지");
        model.addAttribute("userId", info);
        model.addAttribute("trainer", trainerService.searchTrainer(tIdx));
        model.addAttribute("review", reviewService.showReview(tIdx));

        //터미널 확인용
        List<TrainerSearchDto> trainer = trainerService.searchTrainer(tIdx);
        System.out.println(trainer.toString());

        return "content/trainer/trainerDetailPage";
    }

    @GetMapping("/trainer/trainerSearch")
    public String viewTrainerSearch(Model model, @AuthenticationPrincipal User info){
        model.addAttribute("head_title", "트레이너 찾기");
        model.addAttribute("userId", info);
        model.addAttribute("trainers", trainerService.searchAllTrainer());

        return "content/trainer/trainerSearch";
    }

    @GetMapping("/trainer/trainerControl")
    public String trainerControl(Model model, @AuthenticationPrincipal User info){
        model.addAttribute("head_title", "트레이너 관리페이지");
        model.addAttribute("userId", info);
        model.addAttribute("timetable", trainerService.getTimetable(info.getUsername()));

        return "content/trainer/trainerControlpage";
    }
    @PostMapping("/ptTimetableRegister")
    public String timetableRegister(Model model, @AuthenticationPrincipal User info, TimetableDto timetable, int tIdx){
        model.addAttribute("head_title", "트레이너 관리페이지");
        model.addAttribute("userId", info);
        model.addAttribute("timetable", trainerService.getTimetable(info.getUsername()));
        trainerService.registerTimetable(timetable, info.getUsername());
        trainerService.updateTimetable(timetable, info.getUsername());
        trainerService.deleteTimetable(tIdx);
        //3개 한번에 되는지 테스트 해보기

        return "content/trainer/trainerControlpage";
    }
}
