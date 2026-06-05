/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 */
package com.tpfh.fintech.modules.job.task;

import com.tpfh.fintech.modules.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="noticeTask")
public class NoticeTask {
    protected Logger logger = LoggerFactory.getLogger(NoticeTask.class);
    @Autowired
    private MailService mailService;
}

