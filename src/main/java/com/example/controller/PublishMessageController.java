package com.example.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.Greeting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "/publish", consumes = "application/json")
@ComponentScan("com.example")
// @Configuration
@RequestMapping(value = "/publish", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PublishMessageController {
	private final AtomicLong counter = new AtomicLong();

	@ApiOperation(httpMethod = "POST", value = "Publishes message for event audit.", notes = "Pubish event audit messages.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Message received."),
			@ApiResponse(code = 400, message = "Bad Request."),
			@ApiResponse(code = 401, message = "Authentication Failed."),
			@ApiResponse(code = 500, message = "Internal Server Error."),
			@ApiResponse(code = 501, message = "Service Unavailable.") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-DFS-JWT", required = true, dataType = "string", paramType = "header", value = "JWT token header"),
			@ApiImplicitParam(name = "ROUTING-KEY", required = true, dataType = "string", paramType = "header") })
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "firstName" + " lastName", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), "Hello " + name);
		// String.format(template, name));
	}

}
