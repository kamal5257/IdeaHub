package com.idea.hub.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idea.hub.common.UserConstant;
import com.idea.hub.constant.APPServiceCode;
import com.idea.hub.dto.ErrorModel;
import com.idea.hub.dto.ResponseDto;
import com.idea.hub.dto.ResponseModel;
import com.idea.hub.dto.UserRegisterationDto;
import com.idea.hub.model.AuthenticationRequest;
import com.idea.hub.model.AuthenticationResponse;
import com.idea.hub.model.ChangePass;
import com.idea.hub.model.EditUser;
import com.idea.hub.model.User;
import com.idea.hub.model.UserTokens;
import com.idea.hub.repository.TokenRepository;
import com.idea.hub.repository.UserRepository;
import com.idea.hub.security.MyUserDetailService;
import com.idea.hub.service.UserServices;
import com.idea.hub.util.JwtUtil;
import com.idea.hub.util.StringUtils;
import com.idea.hub.util.Validation;


@RestController
public class Controller {
	private static final Logger              LOGGER = Logger.getLogger( Controller.class );

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailService userDetailService;

	@Autowired
	private UserServices services;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private Validation validation;

	@GetMapping("/testApi")
	public String testApi() {
		return "successfully created first API";
	}

	private List<ErrorModel> setErrorData(APPServiceCode serviceCode, List<ErrorModel> errorModels
			/*Translator translator*/) {
		ErrorModel errorDto = new ErrorModel();
		errorDto.setStatusCode(serviceCode.getStatusCode());
		errorDto.setStatusDesc(serviceCode.getStatusDesc());
		errorModels.add(errorDto);
		return errorModels;
	}

	// ####################### Get get all users API
	// ###################################
	@GetMapping("/users")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();

	}

	// ####################### Login and Create Token
	// ######################################
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>("Bad credentials", HttpStatus.NOT_FOUND);
		} catch (UsernameNotFoundException e) {
			return new ResponseEntity<>("Bad credentials", HttpStatus.NOT_FOUND);
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		if (jwt != null) {
			// User user =
			// this.userRepository.getByEmail(authenticationRequest.getUsername());
			UserTokens userToken = this.tokenRepository.getByUsername(authenticationRequest.getUsername());
			userToken.setToken(jwt);
			tokenRepository.save(userToken);
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		}
		return ResponseEntity.ok(HttpStatus.NOT_FOUND);
	}

	// ######################## Singup or create new Acount
	// ######################################
	@PostMapping("/register")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseModel<ResponseDto> createUser(@RequestBody UserRegisterationDto user)
	{
			APPServiceCode serviceCode = null;
		 	ResponseModel<ResponseDto> responseModel = new ResponseModel<>();
	        ResponseDto responseDto = new ResponseDto();
	        List<ErrorModel> errorDtos = new ArrayList();
	        serviceCode = validation.validateUser(user);
	        System.out.println("S CODDDEE "+serviceCode);
	        if ( !(StringUtils.isValidObj( serviceCode ) ) ){
	        	try {
	        		this.services.save(user);
	        		UserTokens userTokens = new UserTokens(user.getEmail(),"");
	        		this.tokenRepository.save(userTokens);
	        		errorDtos = setErrorData(APPServiceCode.NP_SERVICE_001, errorDtos);
	        	}catch(Exception e) {
	        		LOGGER.info(e);
	        	}
	        }
	        else {
	        	errorDtos = setErrorData(serviceCode, errorDtos);
	        }
		if ( StringUtils.isValidCollection( errorDtos ) )
        {
            responseModel.setErrors( errorDtos );
        }
		else
        {
            ResponseDto resDto = new ResponseDto( APPServiceCode.NP_SERVICE_001
                    .getStatusCode(),  APPServiceCode.NP_SERVICE_001.getStatusDesc());
            responseModel.setData( resDto );
            LOGGER.info( "saveSurveyData :: response :: success :: "
                    +" :: "+ serviceCode );
        }
		return responseModel;
	}

	// ############################# Give access to user by ADMIN or MODE
	// ############################
	@GetMapping("/access/{Id}/{userRole}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODE')")
	public String giveAcessToUser(@PathVariable long Id, @PathVariable String userRole, Principal principal) {
		User user = userRepository.findById(Id).get();
		List<String> activeRoles = getRolesByLoggedInUser(principal);
		String newRole = "";
		if (activeRoles.contains(userRole)) {
			newRole = user.getRoles() + "," + userRole;
			user.setRoles(newRole);
		}
		userRepository.save(user);
		return "Hi " + user.getName() + " New Role assigned to you by " + principal.getName();
	}

	private List<String> getRolesByLoggedInUser(Principal principal) {
		String roles = getLoggedInUser(principal).getRoles();
		List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if (assignRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
		}

		if (assignRoles.contains("ROLE_MODE")) {
			return Arrays.stream(UserConstant.MODE_ACCESS).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	private User getLoggedInUser(Principal principal) {
		return userRepository.findByEmail(principal.getName()).get();
	}

	// ######################## Current Logged In User
	// #################################
	@GetMapping("/user")
	// @PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<?> Home(Principal principal) {
		try {
			User user = userRepository.getByEmail(principal.getName());
			return ResponseEntity.of(Optional.of(user));
		} catch (Exception e) {
			return new ResponseEntity<>("Session expired !!", HttpStatus.NOT_FOUND);
		}
	}

	// ########################## Get USER by userID ###############################
	@GetMapping("/user/{Id}")
	public ResponseEntity<?> getUser(@PathVariable long Id) {
		Optional<User> user = userRepository.findById(Id);
		if (user.isEmpty()) {
			return new ResponseEntity<>("Record not found !", HttpStatus.NOT_FOUND);

		}
		return ResponseEntity.of(Optional.of(user));
	}

	// ############################## FORGET PASSWORD ############################
	@PutMapping("/user/forgetPass")
	public ResponseEntity<?> changePass(@RequestBody ChangePass changePass, Principal principal) {
		String oldPass = changePass.getOldPass();
		String newPass = changePass.getNewPass();
		String confirmPass = changePass.getComfirmPass();

		System.out.println("new " + oldPass);
		System.out.println("new " + newPass + "\n" + confirmPass);

		String userName = principal.getName();
		User currentUser = this.userRepository.getByEmail(userName);

		if (oldPass.equals(currentUser.getPassword())) {
			if (newPass.equals(confirmPass)) {
				currentUser.setPassword(newPass);
				this.userRepository.save(currentUser);
				return new ResponseEntity<>("Password changed successfully.", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Password Doesn't Matched ! try again.", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("Incorrect old password! try again.", HttpStatus.BAD_REQUEST);
	}

	// ############################ UPDATE USER BY ADMIN
	// ##############################
	@PutMapping("/edit/user/{Id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> editUser(@PathVariable("Id") long Id, @RequestBody EditUser editUser,
			Principal principal) {
		try {
			User userToEdit = this.userRepository.getById(Id);

			userToEdit.setDOB(editUser.getDOB());
			userToEdit.setMobile(editUser.getMobile());
			userToEdit.setName(editUser.getName());
			userToEdit.setRoles(editUser.getRoles());
			this.userRepository.save(userToEdit);

		} catch (Exception e) {
			return new ResponseEntity<>("Record not found !", HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<>("Successfully Updated", HttpStatus.OK);

	}

	// ######################## LOG OUT #########################################
	@RequestMapping(value = "/logout/{Id}")
	public ResponseEntity<?> expireTokken(@PathVariable long Id) throws Exception {

		User user = this.userRepository.getById(Id);
		String userName1 = user.getEmail();

		UserTokens userTokens = this.tokenRepository.getByUsername(userName1);

		try {
			userTokens.setToken("");
			this.tokenRepository.save(userTokens);
			return new ResponseEntity<>("Logout Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid User", HttpStatus.OK);
		}

	}

}
