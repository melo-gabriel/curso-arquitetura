package br.com.byteup.hellodocker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("docker")
@RestController
public class HelloDockerController {

	@GetMapping
	public String helloDocker() {
		return "Hello world! ByteUpAcademy tutorial";
	}
}
