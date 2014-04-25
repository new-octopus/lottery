package com.mediacross.lottery.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.mediacross.lottery.utils.DateUtil.DateFmts;


public class LecaiGenerator {
	public static final String[] red_balls = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33"};
	public static final String[] blue_balls = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"};
	
	public static final List<String> genLecais(int howMany) {
		List<String> lecais = new ArrayList<String>(howMany);
		Set<String> redBall = new HashSet<String>(10);
		while(lecais.size()<howMany) {
			redBall.clear();
			while(redBall.size() < 6) {
				redBall.add(red_balls[RandomUtils.nextInt(33)]);
			}
			String lecai = "";
			for (String ball : redBall) {
				lecai += (ball+" ");
			}
			lecai += blue_balls[RandomUtils.nextInt(16)];
			if (lecais.contains(lecai)) {
				continue;
			} else {
				lecais.add(lecai);
			}
		} 
		return lecais;
	}
	
	static final String sql_tpl = "INSERT INTO lottery VALUES ('%d', '%s', '1', '0', '', '%s', '%s')";

	public static void main(String[] args) throws IOException {
		int idx = 1;
		FileOutputStream fos = new FileOutputStream("C:\\Users\\qaohao\\Desktop\\lecai_lottery.sql");
		for (String lecai : genLecais(20000)) {
			String now = DateUtil.getDate(DateFmts.YYYY_MM_DD_HHMMSS,
					new Date());
			IOUtils.write(String.format(sql_tpl, idx, lecai, now, now)+";\r\n", fos);
			idx++;
		}
		IOUtils.closeQuietly(fos);
	}
}
