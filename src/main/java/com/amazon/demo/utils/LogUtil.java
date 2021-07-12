package com.amazon.demo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Anand B S
 * @date 04 Jul 2021
 */
public class LogUtil {

    public static Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    }
