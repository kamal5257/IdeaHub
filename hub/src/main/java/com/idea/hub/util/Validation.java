package com.idea.hub.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.idea.hub.constant.APPServiceCode;
import com.idea.hub.dto.UserRegisterationDto;

@Component
public class Validation {
	private APPServiceCode 	serviceCode;
	
	@Autowired
	ValidationUtil 			validationUtil;
	public APPServiceCode validateUser(UserRegisterationDto registerationDto) {
		
		if(StringUtils.isValidObj(registerationDto)) {
			if(!validationUtil.isEmailValid(registerationDto.getEmail())) {
				return APPServiceCode.APP_SERVICE_009;
			}
			if(!validationUtil.isNameValid(registerationDto.getName())) {
				return APPServiceCode.APP_SERVICE_010;
			}
		}
		else {
			return APPServiceCode.APP_SERVICE_997;
		}
		return null;
	}

}
