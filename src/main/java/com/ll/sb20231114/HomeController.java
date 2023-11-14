package com.ll.sb20231114;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/calc6")
    @ResponseBody
    int showCalc6(
            int a, int b
    ) {
        return a + b;
    }

    @GetMapping("/calc7")
    @ResponseBody
    boolean showCalc7(
            int a, int b
    ) {
        return a > b;
    }

    @GetMapping("/calc8")
    @ResponseBody
    Person showCalc8(
            String name, int age
    ) {
        return new Person(name, age);
    }

    @GetMapping("/calc9")
    @ResponseBody
    Person2 showCalc9(
            String name, int age
    ) {
        return new Person2(name, age);
    }

    @GetMapping("/calc10")
    @ResponseBody
    Map<String, Object> showCalc10(
            String name, int age
    ) {
        Map<String, Object> personMap = Map.of(
                "name", name,
                "age", age
        );

        return personMap;
    }

    @GetMapping("/calc11")
    @ResponseBody
    List<Integer> showCalc11() { // 자바의 리스트가 JSON 방식으로 이렇게 번역되는구나
        List<Integer> nums = new ArrayList<>() {{ // 3개가 채워진 리스트
            add(10);
            add(-510);
            add(10010);
        }};

        return nums;
    }

    @GetMapping("/calc12")
    @ResponseBody
    List<Person2> showCalc12(
            String name, int age
    ) {
        List<Person2> persons = new ArrayList<>() {{ // 3개가 채워진 리스트, 계속 추가 가능
            add(new Person2(name, age));
            add(new Person2(name + "!", age + 1));
            add(new Person2(name + "!!", age + 2));
        }};

        return persons;
    }

    @GetMapping("/calc13")
    @ResponseBody
    int[] showCalc13() { // 배열, 고정 길이
        int[] nums = new int[]{10, -510, 10010};

        return nums;
    }

    @GetMapping("/calc14")
    @ResponseBody
    String showCalc14() {
        String html = "";

        html += "<div>";
        html += "<input type=\"text\" placeholder=\"내용\">";
        html += "</div>";

        return html;
    }

    @GetMapping("/calc15")
    @ResponseBody
    String showCalc15() {
        StringBuilder sb = new StringBuilder();

        sb.append("<div>");
        sb.append("<input type=\"text\" placeholder=\"내용\">");
        sb.append("</div>");

        return sb.toString();
    }

    @GetMapping("/calc16")
    @ResponseBody
    String showCalc16() {
        String html = "<div><input type=\"text\" placeholder=\"내용\"></div>";

        return html;
    }

    @GetMapping("/calc17")
    @ResponseBody
    String showCalc17() {
        String html = """
                <div>
                    <input type="text" placeholder="내용">
                </div>
                """;

        return html;
    }

    @GetMapping("/calc18")
    @ResponseBody
    String showCalc18() {
        String html = """
                <div>
                    <input type="text" placeholder="내용" value="반가워요.">
                </div>
                """;

        return html;
    }

    @GetMapping("/calc19")
    @ResponseBody
    String showCalc19(
            @RequestParam(defaultValue = "") String subject,
            @RequestParam(defaultValue = "") String content
    ) {
        String html = """
                <div>
                    <input type="text" placeholder="제목" value="%s">
                </div>
                <div>
                    <input type="text" placeholder="내용" value="%s">
                </div>
                """.formatted(subject, content);

        return html;
    }
}

@AllArgsConstructor
class Person {
    public String name;
    public int age;
}

@AllArgsConstructor
class Person2 {
    @Getter
    private String name;
    @Getter
    private int age;
}
