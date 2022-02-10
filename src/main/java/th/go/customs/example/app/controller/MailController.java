package th.go.customs.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.go.customs.example.app.service.MailService;
import th.go.customs.example.app.vo.req.FwUserReq;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_MESSAGE;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_STATUS;
import th.go.customs.example.framework.bean.ResponseData;

@RestController
@RequestMapping("api/authen")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MailController {
	
	@Autowired
	MailService mailService;
	
	@GetMapping("/send_mail")
	@ResponseBody
	public ResponseData<?> resetPassword(@RequestBody FwUserReq req) {
		ResponseData<?> response = new ResponseData<>();
		try {
			mailService.sendMail(req);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
