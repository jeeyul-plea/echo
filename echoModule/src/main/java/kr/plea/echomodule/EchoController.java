package kr.plea.echomodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
