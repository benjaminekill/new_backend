package th.go.customs.example.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.go.customs.example.app.model.CountAssetDataModel;
import th.go.customs.example.app.model.FwUserModel;
import th.go.customs.example.app.service.HelloWordService;
import th.go.customs.example.app.vo.res.GetRoleUserPageGroupRes;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_MESSAGE;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_STATUS;
import th.go.customs.example.framework.bean.ResponseData;

@RestController
@RequestMapping("api/test")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelloWordController {
	
	@Autowired
	private HelloWordService helloWordService;
	
	
	private static final Logger LOGGER = LogManager.getLogger(HelloWordController.class);
	
	@GetMapping("/get-show")
	@ResponseBody
	public ResponseData<String> getAll() {
		ResponseData<String> response = new ResponseData<String>();
		try {
			LOGGER.info("Info level log message");
			LOGGER.debug("Debug level log message");
			LOGGER.error("Error level log message");
			response.setData(helloWordService.showText());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/get-profile/{username}")
	@ResponseBody
	public ResponseData<FwUserModel> getProfile(@PathVariable String username) {
		ResponseData<FwUserModel> response = new ResponseData<FwUserModel>();
		try {
			response.setData(helloWordService.getProfile(username));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	
	@GetMapping("/get-list-data")
	@ResponseBody
	public ResponseData<List<CountAssetDataModel>> getListAll() {
		ResponseData<List<CountAssetDataModel>> response = new ResponseData<List<CountAssetDataModel>>();
		try {
			response.setData(helloWordService.getListData());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/get-count-asset/{id}")
	@ResponseBody
	public ResponseData<CountAssetDataModel> getCountAssetById(@PathVariable String id) {
		ResponseData<CountAssetDataModel> response = new ResponseData<CountAssetDataModel>();
		try {
			response.setData(helloWordService.getCountAsset(id));
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
