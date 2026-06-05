/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.mail.SimpleMailMessage
 *  org.springframework.mail.javamail.JavaMailSender
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.mail.service.impl;

import com.tpfh.fintech.modules.mail.service.MailService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service(value="mailService")
public class MailServiceImpl
implements MailService {
    private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMessage(String from, List<String> toList, List<String> copyToList, String subject, String content) throws Exception {
        this.logger.info("\u53d1\u9001\u90ae\u4ef6\u4e3b\u9898:" + subject);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("opreport@tpfh.cntaiping.com");
        if (toList != null) {
            String[] toArr = toList.toArray(new String[toList.size()]);
            message.setTo(toArr);
        }
        if (copyToList != null) {
            String[] copyToArr = copyToList.toArray(new String[copyToList.size()]);
            message.setCc(copyToArr);
        }
        message.setSubject(subject);
        message.setText(content);
        this.javaMailSender.send(message);
        this.logger.info("\u6b63\u5e38\u7ed3\u675f\u53d1\u9001\u90ae\u4ef6\u4e3b\u9898:" + subject);
    }

    @Override
    public void sendMessage(String to, String subject, String content) throws Exception {
        this.logger.info("\u53d1\u9001\u90ae\u4ef6\u4e3b\u9898:" + subject);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("opreport@tpfh.cntaiping.com");
        message.setTo(to);
        this.logger.info(to);
        message.setSubject(subject);
        message.setText(content);
        this.javaMailSender.send(message);
        this.logger.info("\u6b63\u5e38\u7ed3\u675f\u53d1\u9001\u90ae\u4ef6\u4e3b\u9898:" + subject);
    }
}

