package com.idea.hub.constant;

import com.idea.hub.util.StringUtils;

public enum APPServiceCode {
    APP_SERVICE_001("APP_SERVICE_001", "Your request has been processed successfully"),
//    APP_SERVICE_002("APP_SERVICE_002", "Oops! It seems your credentials did not match with our records"),
//    APP_SERVICE_003("APP_SERVICE_003", "Survey is already submitted for this user."),
//    APP_SERVICE_004("APP_SERVICE_004", "Survey link is not generated for this respondent"),
//    APP_SERVICE_005("APP_SERVICE_005", "Survey link is already generated using this mobile no."),
//    APP_SERVICE_006("APP_SERVICE_006", "Failed to send survey link mail."),
//    APP_SERVICE_007("APP_SERVICE_007", "Failed to generate survey link."),
    APP_SERVICE_008("APP_SERVICE_008", "Invalid mobile no."), 
    APP_SERVICE_009("APP_SERVICE_009", "Invalid email-id."),
    APP_SERVICE_010("APP_SERVICE_010", "Invalid first name."), 
    APP_SERVICE_011("APP_SERVICE_011", "Invalid last name."),
    APP_SERVICE_012("APP_SERVICE_012", "Invalid State ID."), 
    APP_SERVICE_013("APP_SERVICE_013", "Invalid user ID."),
    APP_SERVICE_014("APP_SERVICE_014", "Invalid isFillSurvey."),
    APP_SERVICE_015("APP_SERVICE_015", "Invalid Respondent Type."), 
    APP_SERVICE_016("APP_SERVICE_016", "Invalid transaction ID."),
    APP_SERVICE_017("APP_SERVICE_017", "Invalid Questionnaire ID."),
    APP_SERVICE_018("APP_SERVICE_018", "Invalid otp."),
    APP_SERVICE_019("APP_SERVICE_019", "Invalid templateId."), 
    APP_SERVICE_020("APP_SERVICE_020", "Empty Sms Body."),
    APP_SERVICE_021("APP_SERVICE_021", "Survey link is already generated for this user."),
    APP_SERVICE_022("APP_SERVICE_022", "Invalid Survey Type."),
    APP_SERVICE_023("APP_SERVICE_023", "Invalid User OS."),
    APP_SERVICE_024("APP_SERVICE_024", "Invalid App Version."),
   
    APP_SERVICE_994("APP_SERVICE_994", "Failed to send Notification"),
    APP_SERVICE_995("APP_SERVICE_995", "Survey Link not generated"), 
    APP_SERVICE_996("APP_SERVICE_996", "No record found"),
    APP_SERVICE_997("APP_SERVICE_997", "Invalid Request, Validation Error"),
    APP_SERVICE_998("APP_SERVICE_998", "Sorry! Security measures are not being followed"),
    APP_SERVICE_999("APP_SERVICE_999", "Unable to process your request, please try later"),;
    String statusCode = null;
    String statusDesc = null;
    private APPServiceCode( String statusCode, String statusDesc )
    {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public static APPServiceCode getServiceByErrorKey( String errorKey )
    {
        for ( APPServiceCode serviceCode : APPServiceCode.values() )
        {
            if ( StringUtils.equals( serviceCode.getStatusCode(), errorKey ) )
            {
                return serviceCode;
            }
        }
        return APPServiceCode.APP_SERVICE_997;
    }
}
