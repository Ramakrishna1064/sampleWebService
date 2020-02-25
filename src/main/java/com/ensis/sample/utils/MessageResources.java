package com.ensis.sample.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Configuration;


/**
 * @author Venu
 *
 */
@Configuration
public class MessageResources implements MessageSourceAware {

	@Autowired
	public MessageSource messageSource;

	@Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
	
	/**
	 * 
	 * @param messageKey
	 * @return
	 */
	public String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, Locale.ENGLISH);
    }
	
	/**
	 * 
	 * @param messageKey
	 * @param obj
	 * @return
	 */
	public String getMessage(String messageKey, Object[] obj) {
        return messageSource.getMessage(messageKey, obj, Locale.ENGLISH);
    }
}
