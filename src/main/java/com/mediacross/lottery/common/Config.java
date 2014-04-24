package com.mediacross.lottery.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.mediacross.lottery.utils.spring.Properties;


@Repository
public class Config {
	@Properties(name = "jdbc.url")
	private String jdbcUrl;
	@Properties(name = "jdbc.username")
	private String dbUser;
	@Properties(name = "jdbc.password")
	private String dbPswd;
	private String dbHost;
	private String dbPort;
	private String database;
	@Properties(name = "action.package.locator")
	private String actionPackageLocator;
	@Properties(name = "action.template.dir")
	private String actionTemplateDir;
	@Properties(name = "client.token")
	private String clientToken;
	
	/**
	 * @return the actionPackageLocator
	 */
	public String getActionPackageLocator() {
		return actionPackageLocator;
	}

	/**
	 * @return the actionTemplateDir
	 */
	public String getActionTemplateDir() {
		return actionTemplateDir;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getDbHost() {
		parseJdbcUrl();
		return dbHost;
	}

	public String getDbPort() {
		parseJdbcUrl();
		return dbPort;
	}

	public String getDataBase() {
		parseJdbcUrl();
		return database;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPswd() {
		return dbPswd;
	}
	
	public String getClientToken() {
		return clientToken;
	}
	
	void parseJdbcUrl() {
		if (StringUtils.isNotBlank(dbHost) || StringUtils.isNotBlank(dbPort)
				|| StringUtils.isNotBlank(database)) {
			return;
		}
		Matcher matcher = Pattern.compile(
				"jdbc:mysql://([a-zA-z0-9.]+):(\\d+)/(\\w+)\\?[\\w=&]+")
				.matcher(jdbcUrl);
		if (matcher.matches()) {
			dbHost = matcher.group(1);
			dbPort = matcher.group(2);
			database = matcher.group(3);
		} else {
			throw new RuntimeException("mysql jdbc url is invalid, it's '"
					+ jdbcUrl + "'");
		}
	}
}
