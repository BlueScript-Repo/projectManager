package com.projectmanager.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.projectmanager.dao.NotificationDao;
import com.projectmanager.dao.UserDetailsDao;
import com.projectmanager.entity.Notifications;
import com.projectmanager.util.EmailUtils;


@EnableWebMvc
@Controller
public class NotificationController {

    @Autowired
    NotificationDao notificationDao;

    @Autowired
    EmailUtils emailUtils;

    @Autowired
    UserDetailsDao userDetailsDao;

    @RequestMapping(value = "showNotifications", method = RequestMethod.GET)
    public ModelAndView showNotificationsList(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("notifications");

        String userName = (String) session.getAttribute("userName");

        ArrayList<Notifications> notificationListInbox = notificationDao.getNotification("userName", userName, "status", "INBOX");

        ArrayList<Notifications> notificationListSent = notificationDao.getNotification("userName", userName, "status", "SENT");

        StringBuilder builderInbox = new StringBuilder();


        for (Notifications notification : notificationListInbox) {
            String msg = notification.getBody() + "|" + notification.getReceiver() + "|" + notification.getDate() + "|" + notification.getAttachmentNames() + "|" + notification.getSubject() + "|" + notification.getId();

            builderInbox.append(" <a style=\" color: black;\n" +
                    "\" href=\"#\" class=\"list-group-item \" onclick=\"ShowMailContent('" + msg + "');\"> " +
                    "                            <span class=\"name label label-info\" style=\"min-width: 120px;\n" +
                    "                             display: inline-block;\"><i class=\"fa fa-share\" aria-hidden=\"true\"></i>&nbsp;&nbsp;" + notification.getReceiver() + "</span> <span class=\"\">" + notification.getSubject() + "</span>\n" +
                    "                            <span class=\"text-muted\" style=\"font-size: 11px;\"></span> <span class=\"badge\">" + notification.getDate() + "</span> <span class=\"pull-right\"><span class=\"glyphicon glyphicon-paperclip\">\n" +
                    "                            </span></span></a>");

        }

        StringBuilder builderSent = new StringBuilder();

        for (Notifications notification : notificationListSent) {
            String msg = notification.getBody() + "|" + notification.getReceiver() + "|" + notification.getDate() + "|" + notification.getAttachmentNames() + "|" + notification.getSubject() + "|" + notification.getStatus() + "|" + notification.getId();
            builderSent.append(" <a style=\"\n" +
                    "    color: black;\n" +
                    "\"href=\"#\" class=\"list-group-item\" onclick=\"ShowMailContent('" + msg + "');\"> <i class=\"fa fa-check\" aria-hidden=\"true\"></i>" +

                    "                        &nbsp;&nbsp;<span class=\"name label label-info\" style=\"min-width: 120px;\n" +
                    "                                display: inline-block;\">" + notification.getReceiver() + "</span> <span class=\"\">" + notification.getSubject() + "</span>\n" +
                    "                            <span class=\"text-muted\" style=\"font-size: 11px;\"></span> <span class=\"badge\">" + notification.getDate() + "</span> <span class=\"pull-right\"><span class=\"glyphicon glyphicon-paperclip\">\n" +
                    "                            </span></span></a>");
        }

        modelAndView.addObject("notificationListInboxVal", builderInbox.toString());
        modelAndView.addObject("notificationListSentVal", builderSent.toString());

        return modelAndView;
    }


    @RequestMapping(value = "downloadAttachment", method = RequestMethod.GET)
    public void downloadAttachment(HttpSession session, HttpServletResponse response, String fileName) {
        if (null == fileName) {
            return;
        }
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);

            String userName = (String) session.getAttribute("userName");
            String fileLocation = System.getProperty("java.io.tmpdir") + "/" + userName + "/";
            OutputStream out = response.getOutputStream();

            FileInputStream fIn = new FileInputStream(new File(fileLocation + fileName));

            byte[] bytes = IOUtils.toByteArray(fIn);

            int length = bytes.length;

            out.write(bytes, 0, length);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @RequestMapping(value = "sendNotification", method = RequestMethod.POST)
    public @ResponseBody
    boolean sendNotification(HttpSession session, Notifications notification, int id, String dateValue) {
        boolean success = true;
        try {
            String userName = (String) session.getAttribute("userName");
            String sender = userDetailsDao.getEmailAddress(userName);

            emailUtils.sendMessageWithAttachment(sender, notification.getReceiver(), notification.getSubject(), notification.getBody(), notification.getAttachmentNames(), userName);
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
//            Date date = format.parse(dateValue.substring(0, dateValue.length() - 2));

//            notification.setDate(new SimpleDateFormat("dd/mm/yyyy").format(dateValue.substring(0, dateValue.length() - 2)));
//            notification.setId(id);
//            notification.setUserName(userName);
//            notification.setStatus("SENT");
            String status = "SENT";

            notificationDao.saveOrUpdate(id, status);
        } catch (Exception ex) {
            success = false;
            ex.printStackTrace();
        }

        return success;
    }
}
