package com.projectmanager.entity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.ManagedBean;

import com.projectmanager.util.NotificationUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.projectmanager.dao.TaxInvoiceDetailsDao;
import com.projectmanager.util.EmailUtils;
import com.projectmanager.util.Principal;

@ManagedBean
public class TaxInvoiceGenerator {

	@Autowired
	TaxInvoiceDetailsDao taxInvoiceDetailsDao;

	@Autowired
	Principal invoiceGenerator;

	@Autowired
	EmailUtils emailUtils;

	@Autowired
	NotificationUtil notificationUtil;

	public void generateAndSendTaxInvoice(TaxInvoiceDetails taxInvoiceDetails, String sender, String userName) {

		invoiceGenerator.createInvoice(taxInvoiceDetails, userName);

		//Instead of sending email directly, notification entry will be pushed in Notifications DB
		/*emailUtils.sendMessageWithAttachment(sender, taxInvoiceDetails.getEmailAddress(),
				taxInvoiceDetails.getTaxInvoiceNo(), false, taxInvoiceDetails.getInvoiceNo());*/

		String fileToAttach = taxInvoiceDetails.getInvoiceNo();

		notificationUtil.pushNotification(userName,taxInvoiceDetails.getEmailAddress(),"Tax Invoice : Hamdule Industries", "Please find attached the Tax Invoice.",fileToAttach.replace("/", "_") + ".pdf" +";"+fileToAttach.replace("/", "_") + "_Annexture.xls","INBOX", new SimpleDateFormat("dd/mm/yyyy").format(new Date()));

		taxInvoiceDetailsDao.saveTaxIvoice(taxInvoiceDetails);
	}

	private final String TAX_INVOICE_ATTACHMENT_NAME = "TaxInvoice.pdf";
}
