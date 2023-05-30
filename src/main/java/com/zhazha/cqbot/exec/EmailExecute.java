package com.zhazha.cqbot.exec;

//@Service
//public class EmailExecute {
//
//	@Resource
//	private JavaMailSender javaMailSender;
//
//	/**
//	 * 发送邮件
//	 *
//	 * @param to      发送目标
//	 * @param subject 邮件主题
//	 * @param content 邮件内容
//	 */
//	public void sendEmail(String to, String subject, String content) {
//		try {
//			MimeMessage message = javaMailSender.createMimeMessage();
//			message.setSubject(subject);
//			message.setSender(new InternetAddress(to,true));
//			message.setText(content);
//			javaMailSender.send(message);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 项目异常发送给 admin
//	 *
//	 * @param subject 邮件主题
//	 * @param content 邮件内容
//	 */
//	public void exceptionSendEmail(String subject, String content) {
//		try {
//			MimeMessage message = javaMailSender.createMimeMessage();
//			message.setSender(new InternetAddress(Constants.toEmail.get(0),true));
//			message.setSubject("异常: " + subject);
//			message.setText(content);
//			javaMailSender.send(message);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
