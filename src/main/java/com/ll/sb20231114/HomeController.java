package com.ll.sb20231114;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러
public class HomeController {

    @GetMapping("/") // 요청을 받으면 아래를 실행하라
    @ResponseBody // 이 함수의 리턴값을 그대로 브라우저에 전송하라는 의미
    String showMain() {
        System.out.println("안녕하세요!!!");
        return "안녕하세요";
    }

    @GetMapping("/about")
    @ResponseBody
    String showAbout() {
        System.out.println("배고파!!!");
        return "개발자 커뮤니티";
    }

    @GetMapping("/calc")
    @ResponseBody
    String showCalc(int a, int b) { // null을 허용하지 않는 기본 타입이다.
        System.out.println("냠냠");
        return "계산 결과 : %d".formatted(a + b);
    }

    @GetMapping("/calc2")
    @ResponseBody
    String showCalc2(Integer a, Integer b) { // null을 허용하는 참조 타입이다.
        System.out.println("쩝쩝");
        return "a : " + a + ", b : " + b;
    }

    @GetMapping("/calc3") // url은 무조건 문자열이기 때문에 숫자도 문자열로
    @ResponseBody
    String showCalc3(
            @RequestParam(defaultValue = "0") int a,
            @RequestParam(defaultValue = "0") int b
    ) {
        return "계산 결과 : %d".formatted(a + b);
    }

    @GetMapping("/calc4")
    @ResponseBody
    String showCalc4(
            @RequestParam(defaultValue = "0") double a,
            @RequestParam(defaultValue = "0") double b
    ) {
        return "계산 결과 : %f".formatted(a + b);
    }

    @GetMapping("/calc5")
    @ResponseBody
    String showCalc5(
            @RequestParam(defaultValue = "-") String a,
            @RequestParam(defaultValue = "-") String b
    ) {
        return "계산 결과 : %s".formatted(a + b);
    }
}
