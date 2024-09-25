package kr.plea.echomodule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.plea.echomodule.DataSource;
import kr.plea.echomodule.custom.DecryptId;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EchoController {

    private final DataSource dataSource;

    @GetMapping("/echo")
    public String echoGet(@RequestParam String message) {
        return message;
    }

    @PostMapping("/echo")
    public String echoPost(@RequestParam String message) {
        return message;
    }

    @GetMapping("/data")
    public String dataGet() {
        return "name: " + dataSource.getName() + " height: " + dataSource.getHeight();
    }

    @PostMapping("/members/{memberId}")
    public String memberEncryptedIDGet (@PathVariable @DecryptId Long memberId) {
        return "memberId: " + memberId;
    }

}
