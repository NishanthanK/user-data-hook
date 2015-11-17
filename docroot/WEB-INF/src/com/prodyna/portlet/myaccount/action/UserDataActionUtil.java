package com.prodyna.portlet.myaccount.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.prodyna.model.UserData;
import com.prodyna.service.UserDataLocalServiceUtil;

public class UserDataActionUtil {

    public static UserData getUserDataFromRequest(ActionRequest request) {
    	ThemeDisplay themeDisplay = 
       		 (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	Calendar c = Calendar.getInstance(Locale.GERMANY);
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	UserData ud = null;
    	try {
    		ud = UserDataLocalServiceUtil.findByC_U(themeDisplay.getCompanyId(), themeDisplay.getUserId());
		} catch (SystemException e) {
			ud =  UserDataLocalServiceUtil.createUserData(0L);
		}
    	if(ud==null){
    		ud =  UserDataLocalServiceUtil.createUserData(0L);
    	}
    		ud.setCompanyId(themeDisplay.getCompanyId());
    		ud.setUserId(themeDisplay.getUserId());
    		ud.setModifiedDate(c.getTime());
    		ud.setT_ShirtSize(ParamUtil.getString(request, "t-shirtSize"));
    		ud.setDateOfBirth(ParamUtil.getDate(request, "dob",formatter));
    		ud.setGlasses(ParamUtil.getBoolean(request, "glasses"));
    		ud.setCoffeesPerDay(ParamUtil.getInteger(request, "coffeesPerDay"));
    		ud.setHeightCm(ParamUtil.getInteger(request, "heightCm"));
    		return ud;
    }
}
