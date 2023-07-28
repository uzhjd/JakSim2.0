package com.twinkle.JakSim.controller.trainer;

import com.twinkle.JakSim.model.dto.review.ReviewRequestDto;
import com.twinkle.JakSim.model.dto.timetable.TimetableInsertDto;
import com.twinkle.JakSim.model.dto.trainer.TrainerInsertDto;
import com.twinkle.JakSim.model.dto.trainer.*;
import com.twinkle.JakSim.model.service.account.FileService;
import com.twinkle.JakSim.model.service.review.ReviewService;
import com.twinkle.JakSim.model.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainerController {
    @Autowired
    TrainerService trainerService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    FileService fileService;
    @GetMapping("/trainer/trainerRegister")
    public String trainerSignUp(Model model,  @AuthenticationPrincipal User info) {

        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 등록");
        model.addAttribute("userId", info);

        return "content/trainer/trainerRegister";
    }

    @PostMapping("/trainerRegister")
    public String trainerSignUp(TrainerInsertDto trainerDto, @AuthenticationPrincipal User info, Model model,
                                @RequestParam("certImage1") MultipartFile certImage,
                                @RequestParam("imagePath1") MultipartFile[] imagePath) throws IOException {
        model.addAttribute("head_title", "트레이너 등록");


        trainerService.TrainerSignUp(trainerDto, info.getUsername(), certImage, imagePath);

        return "redirect:/logout";
    }

    @GetMapping("/trainer/trainerUpdate/{userId}")
    public String trainerUpdate(Model model, @PathVariable("userId") String userId, @AuthenticationPrincipal User info) {

        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 정보수정");
        model.addAttribute("userId", info);
        model.addAttribute("trainer", trainerService.searchTrainer(userId));
        model.addAttribute("product", trainerService.getProduct(userId));
        model.addAttribute("cert", trainerService.getCert(userId));
        model.addAttribute("career", trainerService.getCareer(userId));
        model.addAttribute("imageList", trainerService.getTrainerImage(userId));
        model.addAttribute("name", trainerService.searchTrainerName(userId));

        return "content/trainer/trainerPage";
    }

    @PostMapping("/trainerUpdate")
    public String trainerUpdate(TrainerInsertDto trainerDto, @AuthenticationPrincipal User info, Model model,
                                @RequestParam("certImage1") MultipartFile certImage,
                                @RequestParam("imagePath1") MultipartFile[] imagePath) throws IOException {


        trainerService.updateTrainer(trainerDto, info.getUsername(), certImage, imagePath);

        return "redirect:/trainer/trainerUpdate/" + info.getUsername();
    }

    @PostMapping("/trainerDelete")
    public String trainerDelete(TrainerInsertDto trainerDto, @AuthenticationPrincipal User info) {
        trainerService.deleteTrainer(trainerDto ,info.getUsername());

        return "redirect:/logout";
    }

    @GetMapping("/trainer/{userId}")
    public String viewTrainer(@PathVariable("userId") String userId, @AuthenticationPrincipal User info, Model model) throws SQLException {
        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 상세페이지");
        model.addAttribute("session", info);
        model.addAttribute("trainer", trainerService.searchTrainer(userId));
        model.addAttribute("review", reviewService.showReview(userId));
        model.addAttribute("stars", reviewService.getStarAvgAndCnt(userId));
        model.addAttribute("product", trainerService.getProduct(userId));
        model.addAttribute("cert", trainerService.getCert(userId));
        model.addAttribute("career", trainerService.getCareer(userId));
        model.addAttribute("imageList", trainerService.getTrainerImage(userId));


        return "content/trainer/trainerDetailPage";
    }

    @GetMapping("/trainer/review/{userId}")
    public String viewTrainerReview(@PathVariable("userId") String userId, @AuthenticationPrincipal User info, Model model,
                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                    @RequestParam(value = "filter", defaultValue = "0") int filter) throws SQLException {
        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 리뷰페이지");
        model.addAttribute("session", info);

        model.addAttribute("stars", reviewService.getStarAvgAndCnt(userId));
        model.addAttribute("trainer", trainerService.searchTrainer(userId));

        // 페이징을 위한 데이터 조회
        List<ReviewRequestDto> review = reviewService.showReviewAll(page, pageSize, filter, userId);
        model.addAttribute("review", review);
        int totalReview = reviewService.getStarAvgAndCnt(userId).getReviewCnt();

        model.addAttribute("currentPage", page);
        model.addAttribute("reviewPageSize", pageSize);
        model.addAttribute("filter", filter);


        int totalPages = (int) Math.ceil((double) totalReview / pageSize);

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("pageNumbers", pageNumbers);

        // Pre-calculate previous and next page numbers
        int prevPage = (page > 1) ? page - 1 : 1;
        int nextPage = (page < totalPages) ? page + 1 : totalPages;

        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);


        return "content/trainer/trainerReviewPage";
    }


    @GetMapping("/trainer/trainerSearch")
    public String viewTrainerSearch(Model model, @AuthenticationPrincipal User info,
                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "pageSize", defaultValue = "6") int pageSize,
                                    @RequestParam(value = "filter", defaultValue = "-1") int filter,
                                    @RequestParam(value = "address", defaultValue = "주소를 입력하세요") String address,
                                    @RequestParam(value = "secondWord", defaultValue = "-") String secondWord) {

        if (info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }
        model.addAttribute("head_title", "트레이너 찾기");
        model.addAttribute("userId", info);

        if((!address.equals("주소를 입력하세요"))) {
            // Separate the second word from the address using space as the delimiter
            String[] addressParts = address.split(" ");
            secondWord = addressParts[1];

        }
        model.addAttribute("secondWord", secondWord);

        // 페이징을 위한 데이터 조회
        List<TrainerSearchDto> trainers = trainerService.searchAllTrainer(page, pageSize, filter, secondWord);
        int totalTrainers = trainerService.getTrainerCnt(filter, secondWord);

        model.addAttribute("trainers", trainers);
        model.addAttribute("currentPage", page);
        model.addAttribute("trainersPerPage", pageSize);
        model.addAttribute("filter", filter);

        // Set the address and secondWord variables in the model
        model.addAttribute("address", address);


        int totalPages = (int) Math.ceil((double) totalTrainers / pageSize);

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("pageNumbers", pageNumbers);

        // Pre-calculate previous and next page numbers
        int prevPage = (page > 1) ? page - 1 : 1;
        int nextPage = (page < totalPages) ? page + 1 : totalPages;

        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);

        return "content/trainer/trainerSearch";
    }

    @GetMapping("/trainer/trainerControl")
    public String trainerControl(Model model, @AuthenticationPrincipal User info){

        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 관리페이지");
        model.addAttribute("userId", info);
        model.addAttribute("timetable", trainerService.getTimetable(info.getUsername()));
        model.addAttribute("name", trainerService.searchTrainerName(info.getUsername()));

        return "content/trainer/trainerPage2";
    }
    @PostMapping("/trainer/ptTimetableRegister")
    public String timetableRegister(Model model, @AuthenticationPrincipal User info, TimetableInsertDto timetable){
        model.addAttribute("userId", info);
        trainerService.registerTimetable(timetable, info.getUsername());

        return "redirect:/trainer/trainerControl";
    }
    @PostMapping("/trainer/ptTimetableUpdate")
    public String timetableDelete(@RequestParam("tIdx") int tIdx){
        trainerService.deleteTimetable(tIdx);

        return "redirect:/trainer/trainerControl";
    }

    @GetMapping("/trainer/ptUserInfo")
    public String ptUserInfo(Model model, @AuthenticationPrincipal User info,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                             @RequestParam(value = "ptUserName", defaultValue = "-") String ptUserName){

        if(info != null) {
            model.addAttribute("profile_image", fileService.getSingeProfile(info.getUsername()));
            model.addAttribute("isTrainer", info.getAuthorities().toString().equals("[ROLE_TRAINER]"));
        }

        model.addAttribute("head_title", "트레이너 관리페이지");
        model.addAttribute("userId", info);
        model.addAttribute("ptUser", trainerService.getMyPtUserInfo(page, pageSize, info.getUsername(), ptUserName));
        model.addAttribute("name", trainerService.searchTrainerName(info.getUsername()));
        model.addAttribute("ptUserName", ptUserName);

        // 페이징을 위한 데이터 조회
        int totalPtUsers = trainerService.getPtUserCnt(info.getUsername(), ptUserName);

        model.addAttribute("currentPage", page);
        model.addAttribute("trainersPerPage", pageSize);

        int totalPages = (int) Math.ceil((double) totalPtUsers / pageSize);

        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNumbers.add(i);
        }
        model.addAttribute("pageNumbers", pageNumbers);

        // Pre-calculate previous and next page numbers
        int prevPage = (page > 1) ? page - 1 : 1;
        int nextPage = (page < totalPages) ? page + 1 : totalPages;

        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);

        return "content/trainer/trainerPage3";
    }

    @GetMapping("/modalTest")
    public String modalTest(Model model) {
        model.addAttribute("head_title", "주소 검색");
        return "content/trainer/addressTest";
    }

    // 트레이너 찾기 페이지
    @GetMapping("/address-search")
    public String addressTest() {
        return "content/trainer/addressModal";
    }

    // 트레이너 등록, 수정 페이지
    @GetMapping("/address-search2")
    public String addressTestForRegister() {
        return "content/trainer/addressModalForRegister";
    }

}