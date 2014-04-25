package com.mediacross.lottery;

import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.mediacross.lottery.common.Config;
import com.mediacross.lottery.common.HttpFormat;
import com.mediacross.lottery.common.HttpStatus;
import com.mediacross.lottery.common.error.AppException;
import com.mediacross.lottery.utils.FreemarkerHelper;

@Repository
public class ActionDispatcher implements
		ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
	private static final Logger LOG = LoggerFactory
			.getLogger(ActionDispatcher.class);

	@Autowired
	private Config config;
	@Autowired
	protected FreemarkerHelper freemarkerHelper;
	private Map<String, Action> actionMap;
	private ApplicationContext applicationContext;

	/**
	 * 请求分发。
	 * 
	 * @param context
	 *            请求上下文
	 * @return 响应内容。
	 */
	public Response dispatch(ActionContext context) {
		Action actionBean = null;
		if ((actionBean = actionMap.get(context.getRequstUri())) == null) {
			Response response = new Response();
			response.setStatus(HttpStatus.SC_NOT_FOUND);
			response.setType(HttpFormat.json.getContentType());
			response.setContent(freemarkerHelper.merge(
					"error.json", new AppException("002", "请求资源不存在")));
			return response;
		} else {
			LOG.debug("请求uri[" + context.getRequstUri() + "]交给actionBean["
					+ actionBean + "]处理。");
			return actionBean.request(context);
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			buildActionMap();
			LOG.info("actionMap资源加载完毕。");
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	private void buildActionMap() {
		String[] beanNames = applicationContext
				.getBeanNamesForType(Action.class);
		if (ArrayUtils.isEmpty(beanNames)) {
			return;
		}

		actionMap = new Hashtable();
		for (String beanName : beanNames) {
			Action actionBean = (Action) applicationContext.getBean(beanName);
			String baseUri = actionBean.getClass().getPackage().getName()
					.replaceFirst(config.getActionPackageLocator(), "")
					.replace('.', '/').replaceFirst("/", "");
			if (StringUtils.isNotBlank(baseUri)) {
				baseUri += "/";
			}
			String uri = baseUri
					+ StringUtils
							.removeEndIgnoreCase(beanName, "action")
							.replaceAll(
									String.format("%s|%s|%s",
											"(?<=[A-Z])(?=[A-Z][a-z])",
											"(?<=[^A-Z])(?=[A-Z])",
											"(?<=[A-Za-z])(?=[^A-Za-z])"), "_")
							.toLowerCase();
			actionMap.put(uri, actionBean);

			LOG.info("uri：" + uri + ", actionBean：" + actionBean);
		}
	}

}