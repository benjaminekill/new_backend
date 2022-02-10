package th.go.customs.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import th.go.customs.example.app.service.ReadExcelService;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_MESSAGE;
import th.go.customs.example.framework.bean.ProjectConstant.RESPONSE_STATUS;
import th.go.customs.example.framework.bean.ResponseData;

@RestController
@RequestMapping("api/excel")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReadExcelController {
	
	@Autowired
	private ReadExcelService readExcelService;
	
	@PostMapping("/read_excel")
	@ResponseBody
	public ResponseData<?> resetPassword(@RequestParam MultipartFile files) {
		ResponseData<?> response = new ResponseData<>();
		try {
			readExcelService.readExcel(files);
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
