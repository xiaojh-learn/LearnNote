package com.study.logging;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * @author XH
 * @Package com.study.logging
 * @date 2020/2/19 16:30
 */
public class loggingTest {
    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        logger.setLevel(Level.WARNING);
        logger.info("start");
        logger.log(Level.WARNING, "warning");
        logger.info("end");
    }
}
