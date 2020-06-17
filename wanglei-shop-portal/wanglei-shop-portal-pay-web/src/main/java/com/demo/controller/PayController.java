package com.demo.controller;

import com.demo.base.BaseWebController;
import com.demo.common.BaseResponse;
import com.demo.feign.PayMentTransacInfoFeign;
import com.demo.feign.PaymentChannelFeign;
import com.demo.pay.out.PayMentTransacDTO;
import com.demo.pay.out.PaymentChannelDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PayController extends BaseWebController {
	@Autowired
	private PayMentTransacInfoFeign payMentTransacInfoFeign;
	@Autowired
	private PaymentChannelFeign paymentChannelFeign;

	@RequestMapping("/pay")
	@ResponseBody
	public String pay(String payToken, Model model) {
		// 1.验证payToken参数
		if (StringUtils.isEmpty(payToken)) {
			setErrorMsg(model, "支付令牌不能为空!");
			return ERROR_500_FTL;
		}
		// 2.使用payToken查询支付信息
		BaseResponse<PayMentTransacDTO> tokenByPayMentTransac = payMentTransacInfoFeign.tokenByPayMentTransac(payToken);
		if (!isSuccess(tokenByPayMentTransac)) {
			setErrorMsg(model, tokenByPayMentTransac.getMsg());
			return ERROR_500_FTL;
		}
		// 3.查询支付信息
		PayMentTransacDTO data = tokenByPayMentTransac.getData();
		model.addAttribute("data", data);
		// 4.查询渠道信息
		List<PaymentChannelDTO> paymentChanneList = paymentChannelFeign.selectAll();
		model.addAttribute("paymentChanneList", paymentChanneList);
		return "index";
	}
}
