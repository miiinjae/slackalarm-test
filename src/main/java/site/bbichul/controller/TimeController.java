package site.bbichul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.bbichul.dto.TimeRequestDto;
import site.bbichul.dto.UserDto;
import site.bbichul.models.Time;

import site.bbichul.security.UserDetailsImpl;
import site.bbichul.service.TimeService;
import site.bbichul.service.UserService;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class TimeController {


    private final TimeService timeService;
    private final UserService userService;

//    @PostMapping("/time")
//    public Time createTime(@RequestBody TimeRequestDto timeRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        // 로그인이 되어 있는 ID
////        Long userId = userDetails.getUser().getId();
//        LocalDate localDate = LocalDate.now();
//
//        // 날짜 쪼개서 저장하기
//        int year = localDate.getYear();
//        timeRequestDto.setYear(year);
//
//        int month = localDate.getMonthValue();
//        timeRequestDto.setMonth(month);
//
//        int day = localDate.getDayOfMonth();
//        timeRequestDto.setDay(day);
//
//        int weekday = localDate.getDayOfWeek().getValue();
//        timeRequestDto.setWeekday(weekday);
//
//        Time time = timeService.upsertTime(timeRequestDto, userDetails.getUser());
//
//        return time;
//
//    }
    // 공부시작 true 보내기
    @PostMapping("/user")
    public void startstudy(@RequestBody UserDto userDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        userService.setStudy(userDto, username);
        System.out.println(userDto);
    }


    //00 시 기준 공부시간
    @PostMapping("/ytime")
    public Time ycreateTime(@RequestBody TimeRequestDto timeRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        // 로그인이 되어 있는 ID
//        Long userId = userDetails.getUser().getId();
        LocalDate localDate = LocalDate.now();

        // 날짜 쪼개서 저장하기
        int year = localDate.getYear();
        timeRequestDto.setYear(year);

        int month = localDate.getMonthValue();
        timeRequestDto.setMonth(month);

        int day = localDate.getDayOfMonth();
        timeRequestDto.setDay(day);

        int weekday = localDate.getDayOfWeek().getValue();
        timeRequestDto.setWeekday(weekday);

        int yesterdayTime = timeRequestDto.getYesterday_time();

        Time time;

        if (yesterdayTime != 0){
            time = timeService.upsertStudy(timeRequestDto, userDetails.getUser());

        }else {
            time = timeService.upsertTime(timeRequestDto, userDetails.getUser());
        }
        System.out.println(timeRequestDto);
        System.out.println(yesterdayTime);

        return time;

    }
}