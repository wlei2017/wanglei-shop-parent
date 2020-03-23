package com.demo.weixin.mp.handler;

import com.demo.utils.RedisUtil;
import com.demo.utils.RegexUtils;
import com.demo.weixin.mp.builder.TextBuilder;
import com.demo.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */

@Slf4j
@Component
public class MsgHandler extends AbstractHandler {

	@Value("${mayikt.weixin.registration.code.message}")
	private String replayContent;

	@Value("${mayikt.weixin.default.registration.code.message}")
	private String defaultReplayContent;

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService,
			WxSessionManager sessionManager) {

		if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
			// TODO 可以选择将消息保存到本地
		}

		// 当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
		try {
			if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
					&& weixinService.getKefuService().kfOnlineList().getKfOnlineList().size() > 0) {
				return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
						.toUser(wxMessage.getFromUser()).build();
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		// 1.获取用户输入的内容
		String fromContent = wxMessage.getContent();
		// 2.校验输入的手机号格式是否正确
		if (RegexUtils.checkMobile(fromContent)){
			// 3.生成随机的四位验证码
			int registCode = registCode();
			String content = String.format(replayContent,registCode);

			// 4.将验证码存放到redis中
			redisUtil.setString(Constants.WEIXINCODE_KEY + fromContent,registCode + "",Constants.WEIXINCODE_TIMEOUT);
			return new TextBuilder().build(content,wxMessage,weixinService);
		}
		log.info("fromContent:" + fromContent);
		// TODO 组装回复消息
		// String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);

		return new TextBuilder().build(defaultReplayContent, wxMessage, weixinService);

	}

	/**
	 * 生成四位随机验证码
	 * @return
	 */
	private int registCode(){
		//Math.random()：产生一个[0，1)之间的随机数。
		//返回指定范围的随机数(m-n之间)的公式
		//Math.random()*(n+1-m)+m
		int registCode = (int) (Math.random()*(9999+1-1000) + 1000);
		return registCode;
	}

}
