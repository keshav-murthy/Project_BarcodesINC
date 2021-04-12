package commons;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MailHelper extends BasePage {

	static String mailSubject;

	public MailHelper(WebDriver driver) {
		super(driver);
	}

	private static final Logger lOGGER = LogManager.getLogger(MailHelper.class.getName());

	public static String MailFound(String subject, String username, String password) throws Exception {

		String mailFromClient = null;
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		if (username.contains(InitializePropertyFile.property.getProperty("TC05_username")))
			store.connect("imap.gmail.com", username, password);
		else
			store.connect("imap.mail.yahoo.com", username, password);

		Folder folder = store.getFolder("Inbox");
		folder.open(Folder.READ_WRITE);

		lOGGER.info("Total Message:" + folder.getMessageCount());
		lOGGER.info("Unread Message:" + folder.getUnreadMessageCount());

		Message[] messages = null;
		boolean isMailFound = false;
		messages = folder.getMessages();

		for (Message mail : messages) {

			if (!mail.isSet(Flags.Flag.SEEN) && mail.getSubject().contains(subject)) {

				lOGGER.info("Subject: " + mail.getSubject());
				lOGGER.info("From: " + mail.getFrom()[0]);

				mailSubject = mail.getSubject();
				System.out.println("***********" + mail.getContentType());
				MimeMultipart mimeMultipart = (MimeMultipart) mail.getContent();
				mailFromClient = getTextFromMimeMultipart(mimeMultipart);
				isMailFound = true;
			}
		}
		System.out.println("------------" + mailFromClient);

		if (!isMailFound) {
			lOGGER.info("Unread Message:" + folder.getUnreadMessageCount());
			pause(Timeouts.WAITFORMAIL);
			mailFromClient = MailFound(subject, username, password);

		}
		return mailFromClient;
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

	public static String fetchResolveComplaintURL(String body) {
		String s = body.substring(0, body.indexOf("Resolve Your Complaint Now"));
		return s.substring(s.lastIndexOf("https"), s.indexOf("/complaints") + 20);
	}

	public static String getReferenceNumber() {
		return mailSubject.substring(mailSubject.indexOf(": ") + 2);
	}

	public static String fetchUpdateComplaintURL(String body) {

		String s = body.substring(0, body.indexOf("Update Us About Your Complaint"));
		return s.substring(s.lastIndexOf("https"), s.indexOf("261A273A0A2756"));

	}

	public static void deleteAllInboxMails(String username, String password) throws MessagingException {

		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");

		if (username.contains(InitializePropertyFile.property.getProperty("TC05_username")))
			store.connect("imap.gmail.com", username, password);
		else
			store.connect("imap.mail.yahoo.com", username, password);
		Folder folder = store.getFolder("Inbox");
		folder.open(Folder.READ_WRITE);

		lOGGER.info("Total Message:" + folder.getMessageCount());
		lOGGER.info("Unread Message:" + folder.getUnreadMessageCount());

		if (folder.isOpen()) {
			Message[] messages = folder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				messages[i].setFlag(Flags.Flag.DELETED, true);
			}
		}
		lOGGER.info("Total Message:" + folder.getMessageCount());
		lOGGER.info("Unread Message:" + folder.getUnreadMessageCount());
	}
}