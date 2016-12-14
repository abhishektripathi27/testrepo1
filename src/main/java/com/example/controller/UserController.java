package com.example.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Users;
import com.example.repos.UsersRepository;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@ComponentScan("com.example")
@Api(value = "/publish", consumes = "application/json")
@RequestMapping(value = "/publish", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UsersRepository usersRepository;

	@ApiOperation(httpMethod = "POST", value = "Publishes message for event audit.", notes = "Pubish event audit messages.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Message received."),
			@ApiResponse(code = 400, message = "Bad Request."),
			@ApiResponse(code = 401, message = "Authentication Failed."),
			@ApiResponse(code = 500, message = "Internal Server Error."),
			@ApiResponse(code = 501, message = "Service Unavailable.") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-DFS-JWT", required = true, dataType = "string", paramType = "header", value = "JWT token header"),
			@ApiImplicitParam(name = "ROUTING-KEY", required = true, dataType = "string", paramType = "header") })
	@RequestMapping("/adduser")
	@ResponseBody
	public String test(@RequestParam Map<String, String> requestParams) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
		logger.debug("Starting...........................................");
		String firstName = requestParams.get("firstName");
		String lastName = requestParams.get("lastName");
		logger.debug("First Name " + firstName);
		logger.debug("Last Name " + lastName);
		Users user = new Users();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		usersRepository.save(user);
		return "Hello " + firstName + " " + lastName;
	}

}
