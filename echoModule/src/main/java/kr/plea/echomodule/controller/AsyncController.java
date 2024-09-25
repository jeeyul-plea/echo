package kr.plea.echomodule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.plea.echomodule.service.AsyncService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/async")
@RequiredArgsConstructor
public class AsyncController {
	private final AsyncService asyncService;

	@GetMapping
	public void async() {
		for (int i = 0; i< 31; i++) {
			asyncService.asyncMethod(i);
		}

	}
}
