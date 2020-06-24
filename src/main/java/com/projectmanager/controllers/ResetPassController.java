package com.projectmanager.controllers;

import java.io.File;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amazonaws.services.iot.model.AlertTarget;
import com.projectmanager.dao.ResetCodeDao;
import com.projectmanager.dao.UserDetailsDao;
import com.projectmanager.entity.ResetCode;
import com.projectmanager.entity.SessionEntry;
import com.projectmanager.entity.UserDetails;
import com.projectmanager.util.EmailUtils;

@Controller
@EnableWebMvc
public class ResetPassController {

	@Autowired
	UserDetailsDao userDetailsDao;

	@Autowired
	EmailUtils emailUtils;

	@Autowired
	ResetCodeDao resetCodeDao;
	


	final static String ResetPassword = "resetPassword";

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView resetPassword() {
		ModelAndView model = new ModelAndView(ResetPassword);
		model.addObject("userNameInput", userNameInput);
		model.addObject("resetCodeInput", "");
		model.addObject("newPassword", "");
		model.addObject("confirmPassword", "");
		model.addObject("confirmButton", confirmButton);
		model.addObject("confirmMessage", "");
		model.addObject("errorMessage", "");

		return model;
	}

	public int randomCode() {
		Random r = new Random(System.currentTimeMillis());
		return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView setPassword(HttpServletRequest request,
			HttpServletResponse response, UserDetails userDetails) {

		ModelAndView model = new ModelAndView(ResetPassword);

		String resetCode = request.getParameter("randomCode");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		UserDetails userIsPresent = userDetailsDao.isUserPresent(userName);
		System.out.println("user present " +userIsPresent);

		if (password == null) {
			if (resetCode == null) {
				if(userIsPresent != null){	
					
				int randomNumber = randomCode();
				String randomCode = String.valueOf(randomNumber);

				String recipient = userDetailsDao.getEmailAddress(userName);
				String subject = "Forget Password Code.";
				String sender = "udaybajare2@gmail.com";
				String emailMessage = "Please use this code to reset password -"
						+ randomCode;
				File[] filesToattach = null;

				resetCodeDao.saveResetCode(new ResetCode(randomCode, userName));
				emailUtils.sendEmail(subject, sender, recipient, emailMessage,
						filesToattach);

				System.out.print("Email Send");
				model.addObject("userNameInput", userNameInput);
				model.addObject("resetCodeInput", resetCodeInput);
				model.addObject("newPassword", "");
				model.addObject("confirmPassword", "");
				model.addObject("confirmMessage", "");
				model.addObject("confirmButton", confirmButton);
				model.addObject("errorMessage", "");
				}
				else{
					model.addObject("userNameInput", "");
					model.addObject("resetCodeInput", "");
					model.addObject("newPassword", "");
					model.addObject("confirmPassword", "");
					model.addObject("confirmButton", "");
					model.addObject("confirmMessage", "");					
					model.addObject("errorMessage", errorMessage);
				}

			} else {
				String code = resetCodeDao.getCode(userName);
				if (resetCode.equals(code)) {
					model.addObject("userNameInput", userNameInput);
					model.addObject("resetCodeInput", "");
					model.addObject("newPassword", newPassword);
					model.addObject("confirmPassword", confirmPassword);
					model.addObject("confirmMessage", "");
					model.addObject("confirmButton", confirmButton);
					model.addObject("errorMessage", "");
				}
				else{
					
				}
			}

		} else {
			String confirmPassword = request.getParameter("password");
			if (password.equals(confirmPassword)) {

				boolean updatepassword = resetCodeDao.setNewPassword(userName, confirmPassword);
				System.out.print("update password");
				if (true) {
					model.addObject("userNameInput", "");
					model.addObject("resetCodeInput", "");
					model.addObject("newPassword", "");
					model.addObject("confirmPassword", "");
					model.addObject("confirmButton", "");
					model.addObject("confirmMessage", confirmMessage);
					model.addObject("errorMessage", "");
				}
			}
		}

		return model;

	}

	public static final String userNameInput = " <label for=\"inputUserName\" class=\"col-md-3 text-md-right control-label col-form-label\">User Name</label>"
			+ "<div class=\"col-md-8\">"
			+ "<input name =\"userName\" type=\"text\" class=\"form-control\" id=\"inputUserName\" placeholder=\"User Name\" required=\"\">"
			+ "<i class=\" form-control-feedback pr-4\"></i></div>";

	public static final String resetCodeInput = "<label for=\"inputResetCode\" class=\"col-md-3 text-md-right control-label col-form-label\">Reset Code</label>"
			+ "<div class=\"col-md-8\">"
			+ "<input name =\"randomCode\" type=\"text\" class=\"form-control\" id=\"inputResetCode\" placeholder=\"enter code\" required=\"\">"
			+ "<i class=\" form-control-feedback pr-4\"></i></div>";
	public static final String newPassword = " <label for=\"newPassword\" class=\"col-md-3 text-md-right control-label col-form-label\"> New Password</label>"
			+ "<div class=\"col-md-8\">"
			+ "<input name =\"password\" type=\"text\" class=\"form-control\" id=\"newPassword\" placeholder=\"New Password\" required=\"\">"
			+ "<i class=\" form-control-feedback pr-4\"></i></div>";

	public static final String confirmPassword = "<label for=\"confirmPassword\" class=\"col-md-3 text-md-right control-label col-form-label\">Confirm Password</label>"
			+ "<div class=\"col-md-8\">"
			+ "<input name =\"password\" type=\"text\" class=\"form-control\" id=\"confirmPassword\" placeholder=\"enter code\" required=\"\">"
			+ "<i class=\" form-control-feedback pr-4\"></i></div>";
	public static final String confirmMessage = "<h2 class=\"page-title medium\">"
			+ "<span class=\"text-default\">Reset Password is done</span></h2>"
			+ "<p class=\"lead\">Thanks You!! Your Password is reset. Please Login </p> ";
	public static final String confirmButton = "<form class=\"form-horizontal\">"
			+ "<button type=\"submit\" class=\"btn btn-default btn-animated\">confirm</button></form>	";
	public static final String errorMessage = "<form action=\"confirm\" method=\"POST\"><h4 class=\"page-title medium\">"
			+ "<span class=\"text-default\">User is invalid</span></h4>"
			+ "<p class=\"lead\">Please Enter Valid User.</p> </form>";
	

}