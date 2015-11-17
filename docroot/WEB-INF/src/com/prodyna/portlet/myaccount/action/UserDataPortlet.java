package com.prodyna.portlet.myaccount.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.prodyna.model.UserData;
import com.prodyna.service.UserDataLocalServiceUtil;

/**
 * Portlet implementation class UserDataPortlet
 */
public class UserDataPortlet extends MVCPortlet {
	
	 public void doView(
             RenderRequest renderRequest,
     RenderResponse renderResponse)
         throws IOException, PortletException {

		 ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		 
		 
		 UserData userData;
		try {
			userData = UserDataLocalServiceUtil.findByC_U(themeDisplay.getCompanyId(),themeDisplay.getUserId());
		} catch (SystemException e) {
			userData =  UserDataLocalServiceUtil.createUserData(0L) ;
		}

         renderRequest.setAttribute("userData", userData);
         include(viewJSP, renderRequest, renderResponse);
     }
	
	public void updateUserData(
			ActionRequest request, ActionResponse response)
		throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute( WebKeys.THEME_DISPLAY);
		UserData userData = 
		        UserDataActionUtil.getUserDataFromRequest(request);
		ArrayList<String> errors = new ArrayList<String>();
		
		boolean userDataValid = UserDataValidator.validateUserData(userData, errors);
		if(userDataValid){
			if(userData.getUserDataId()!=0L){
				userData = UserDataLocalServiceUtil.updateUserData(userData);
			} else {
				userData = UserDataLocalServiceUtil.addUserData(userData, themeDisplay.getCompanyId(), themeDisplay.getUserId() );				
			}
			SessionMessages.add(request, "user-data-saved-successfully");
		} else {
			for(String error: errors){
				SessionErrors.add(request, error);
			}
			SessionErrors.add(request, "fields-required");
			SessionErrors.add(request, "error-updating");
		}
		request.setAttribute("userData", userData); 
	}
	protected String viewJSP = "/html/userdata/view.jsp"; 
}
