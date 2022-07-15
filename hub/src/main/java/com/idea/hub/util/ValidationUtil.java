package com.idea.hub.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {
	 public boolean isEmailValid( String email )
	    {
	        String orgEmail = ( email );
	        boolean result = true;
	        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	        Pattern pattern = Pattern.compile( regex );
	        Matcher matcher = pattern.matcher( orgEmail );
	        result = matcher.matches();
	        if ( !result )
	            return false;
	        return true;
	    }
	 
	   public boolean isNameValid( String name )
	    {
	        name = name.toLowerCase();
	        name = name.trim();
	        char[] charArray = name.toCharArray();
	        for ( int i = 0; i < charArray.length; i++ )
	        {
	            char ch = charArray[i];
	            if ( ! ( ( ch >= 'a' && ch <= 'z' ) || ch == ' ' ) )
	            {
	                return false;
	            }
	        }
	        return true;
	    }

}
