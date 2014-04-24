package com.mediacross.lottery.web;

import java.util.Map;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mediacross.lottery.Action;
import com.mediacross.lottery.ActionContext;
import com.mediacross.lottery.Response;
import com.mediacross.lottery.common.Config;
import com.mediacross.lottery.common.HttpFormat;
import com.mediacross.lottery.common.HttpStatus;
import com.mediacross.lottery.common.error.AppException;
import com.mediacross.lottery.utils.FreemarkerHelper;
import com.mediacross.lottery.utils.LangUtils;

/**
 * Action基类，定义请求整个处理流程：
 * <ul>
 * authenticate -> validate -> execute
 * </ul>
 * 最终其返回值插入到对应模板中。
 * 
 * @author qaohao
 */
public abstract class BaseAction implements Action {
	private static final Logger LOG = LoggerFactory.getLogger(BaseAction.class);

	@Autowired
	protected Config config;
	@Autowired
	protected FreemarkerHelper freemarkerHelper;

	@Override
	public final Response request(ActionContext actionContext) {
		Map paramMap = actionContext.getParamMap();
		LOG.info("请求参数：" + paramMap);

		HttpFormat httpFormat = getResultFormat(actionContext);
		LOG.info("请求内容格式为：" + httpFormat.getFormat());

		try {
			// 参数认证通过且参数合法，则继续响应请求。
			if (authenticate(paramMap) && validate(paramMap)) {
				LOG.info("认证通过且请求参数合法。");

				// 处理请求并将处理结果插入到模板中。
				String template = actionContext.getRequstUri()
						+ httpFormat.getSuffix();
				LOG.info("请求响应的模板：" + template);

				BasicDynaBean dynaBean = (BasicDynaBean) execute(paramMap);
				String content = freemarkerHelper.merge(template,
						dynaBean == null ? null : dynaBean.getMap());

				// 组装Response对象。
				Response response = new Response();
				response.setStatus(HttpStatus.SC_OK);
				response.setType(httpFormat.getContentType());
				response.setContent(content);
				LOG.info("响应内容：" + response);

				return response;
			} else {
				// never pass here
				throw new AppException(10005, "无效请求");
			}
		} catch (AppException e) {
			Response response = new Response();
			response.setStatus(HttpStatus.SC_OK);
			response.setType(httpFormat.getContentType());
			response.setContent(freemarkerHelper.merge(
					"error" + httpFormat.getSuffix(), e));
			LOG.warn(StringUtils.EMPTY, e);
			return response;
		}
	}

	/**
	 * 验证参数。
	 * 
	 * @param paramMap
	 *            参数Map
	 * @return 不合法返回false，否则返回true
	 */
	public boolean validate(Map paramMap) throws AppException {
		return true;
	}

	/**
	 * 执行请求。
	 * 
	 * @param paramMap
	 *            参数Map
	 * @return 处理后的结果。
	 */
	public abstract DynaBean execute(Map paramMap) throws AppException;

	/**
	 * 根据规则验证token有效性。
	 * 
	 * @param paramMap
	 *            参数Map
	 * @return 无效返回false，否则返回true
	 * @throws AppException
	 */
	final boolean authenticate(Map paramMap) throws AppException {
		if (MapUtils.isEmpty(paramMap) || !paramMap.containsKey("sign")) {
			throw new AppException(10002, "无sign签名参数");
		} else {
			String sign = (String) paramMap.remove("sign");
			if (sign.equals(LangUtils.sign(config.getClientToken(), paramMap))) {
				return true;
			} else {
				throw new AppException(10003, "sign签名参数值无效");
			}
		}
	}

	final HttpFormat getResultFormat(ActionContext actionContext) {
		Map paramMap = actionContext.getParamMap();
		// 获取请求内容类型，默认是json
		HttpFormat httpFormat = HttpFormat.json;
		if (paramMap.containsKey("format")) {
			httpFormat = HttpFormat.getInstance(paramMap.get("format")
					.toString());
		} else if (actionContext.getRequstUri().startsWith("widget")) {
			LOG.debug("访问widget目录下资源，返回内容格式是html。");
			httpFormat = HttpFormat.html;
		}
		return httpFormat;
	}
}