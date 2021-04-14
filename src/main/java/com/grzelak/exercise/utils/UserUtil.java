package com.grzelak.exercise.utils;

import java.math.BigDecimal;
import java.math.MathContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserUtil {
	
	private static Logger log = LoggerFactory.getLogger(UserUtil.class);
	
	public static BigDecimal calculate (long followers, long publicRepos) {
		BigDecimal result = new BigDecimal(6);
		try {
			BigDecimal followersBD = new BigDecimal(followers);
			result = result.divide(followersBD, MathContext.DECIMAL128);
			BigDecimal multiplier = new BigDecimal(2).add(new BigDecimal(publicRepos));
			return result.multiply(multiplier);
		} catch (ArithmeticException e) {
			log.error("Arithmetic exception: " + e.getMessage());
			throw e;
		}
	}

}