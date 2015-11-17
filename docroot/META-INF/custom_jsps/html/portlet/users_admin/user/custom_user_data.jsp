<%@ include file="/html/portlet/users_admin/init.jsp" %>
<script src="/html/portlet/users_admin/js/custom_user_data.js"></script>

<style>
<!--
.range {
	color:red;
}
-->
</style>

<span id="<portlet:namespace />"></span>

<script>
	var war = document.getElementById('<portlet:namespace />').id;
	YUI().use(
			  'aui-form-validator',
			  function(Y) {
			    new Y.FormValidator(
			      {
			        boundingBox: '#'+war+'fm',
			        rules: {
			        	<portlet:namespace />heightCm: {
			            digits: true,
			            range: [50,273],
			            required: true
			          }
			        }
			      }
			    );
			  }
			);
</script>

<%
UserData userData;
boolean userDataExists = false;
try {
	userData = UserDataLocalServiceUtil.findByC_U(themeDisplay.getCompanyId(),themeDisplay.getUserId());

} catch (SystemException e) {
	userData = UserDataLocalServiceUtil.createUserData(0L);
}
	if (userData == null){
		userData = UserDataLocalServiceUtil.createUserData(0L);
	}
%>

            <%
	            Calendar dd = CalendarFactoryUtil.getCalendar();
	            if(userDataExists){
	            	dd.setTime(userData.getDateOfBirth());
	            } 
	            int dayOfMonth = dd.get(Calendar.DAY_OF_MONTH);
				int month = dd.get(Calendar.MONTH) +1;
				int year = dd.get(Calendar.YEAR);
			
			
			request.setAttribute("dayOfMonth", dayOfMonth);
			request.setAttribute("month",month);
			request.setAttribute("year", year);
			String[] t_shirtSizes = {"xs", "s", "m", "l", "xl", "xxl", "xxxl"};
			%>
			

<script>

	var dayOfMonth = parseInt('<%= request.getAttribute("dayOfMonth")%>');
	var month = parseInt('<%= request.getAttribute("month")%>');
	var year = parseInt('<%= request.getAttribute("year")%>');

</script>


	<aui:fieldset>
		<aui:input name="userDataId" type="hidden" />
		<aui:input name="creationDate" type="hidden" />
		<aui:input name="modifiedDate" type="hidden"  />
		<liferay-ui:error key="t-shirt-size-required" message="t-shirt-size-required" />
		<aui:select name='t-shirtSize' disabled="false">
		<% for(int i = 0 ; i <= 6 ; i++){ %>
				<%
					if( userData.getT_ShirtSize().equals(t_shirtSizes[i])) {
				%>
					<aui:option value='<%=t_shirtSizes[i]%>' selected="true" ><%= t_shirtSizes[i].toUpperCase() %></aui:option>
				<%
					} else {
				%>
					<aui:option value='<%=t_shirtSizes[i]%>' ><%= t_shirtSizes[i].toUpperCase() %></aui:option>
				<%
					}
				%>
		<% } %>
		</aui:select>			

		<liferay-ui:error key="date-of-birth-required" message="date-of-birth-required" />
		<liferay-ui:error key="valid-date-of-birth-required" message="valid-date-of-birth-required" />
		<liferay-ui:error key="dob-must-be-after-1891" message="dob-must-be-after-1891" />
    <div id="dob_wrapper" class="aui-datepicker-example aui-helper-clearfix" >
 		 <aui:input type="text" class="input-large" name="dob" id="dob" 
 		 			value='<%=dayOfMonth+"/"+month+"/"+year%>'  />
 			 			
	</div>
		<liferay-ui:error key="glasses-required" message="glasses-required" />
		<label id="wear-glasses"><liferay-ui:message key="wear-glasses" /></label>
		<aui:input inlineLabel="right"
				name='<%="glasses"%>' type="radio"
				id='<%="yes"%>' label="yes"
				inlineField="true" checked="<%=userData.getGlasses()%>" />
						
		<aui:input inlineLabel="right"
				name='<%="glasses"%>' type="radio"
				id='<%="no"%>' label="no" inlineField="true"
				checked="<%=!userData.getGlasses()%>" />
				
		<liferay-ui:error key="coffees-per-day-required" message="coffees-per-day-required" />
		<aui:select name='coffeesPerDay' disabled="false">
			<% for(int i = 0; i <= 20; i++){ %>
				<% if(userData.getCoffeesPerDay() == i) { %>
					<aui:option value='<%=i%>' selected="true"><%=i%></aui:option>
				<% } else { %>
					<aui:option value='<%=i%>'><%=i%></aui:option>				
				<% } %>	
			<% } %>
				<% if(userData.getCoffeesPerDay() == 21) { %>
					<aui:option value='<%=21%>' selected="true" ><liferay-ui:message key="more-than-20" /></aui:option>	
				<% } else { %>
					<aui:option value='<%=21%>'><liferay-ui:message key="more-than-20" /></aui:option>	
				<% } %>	
		</aui:select>						
			<liferay-ui:error key="height-cm-required" message="height-cm-required" />
		<div class="form-group">
		    <label class="control-label" for="heightCm"><liferay-ui:message key="height-in-cm" />:</label>
		    <div class="controls">
		      <input name="<portlet:namespace />heightCm" value="<%= userData.getHeightCm() %>" id="<portlet:namespace />heightCm" class="form-control field-required field-digits" type="text"/>
		    </div>
  		</div>		
	</aui:fieldset>
	

