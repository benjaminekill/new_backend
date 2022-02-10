package th.go.customs.example.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.go.customs.example.app.model.FwUserModel;
import th.go.customs.example.app.repo.jpa.FwUserRepo;
import th.go.customs.example.framework.bean.ResponseData;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsernameController {

	@Autowired
	private  FwUserRepo repository;

	  // Aggregate root
	  // tag::get-aggregate-root[]
	  @GetMapping("/userlist")
	  List<FwUserModel> all() {
	    return repository.findAll();
	  }
	  // end::get-aggregate-root[]

	  @PostMapping("/create-user")
	  FwUserModel newFwUserModel(@RequestBody FwUserModel newFwUserModel) {
	    return repository.save(newFwUserModel);
	  }

		
		@GetMapping("/get-edit-user/{id}")
		public String showUpdateForm(@PathVariable("id") Long id, Model model) {
			FwUserModel data = repository.findById(id).get();
		    model.addAttribute("FwUserModel", data);
		    return "views/edit-reportData";
		}
		
		@PostMapping("/edit-user/{id}")
	    @ResponseBody
		public ResponseData updateUser(@PathVariable("id") Long id, @Valid FwUserModel data, 
		  BindingResult result, Model model) {
			ResponseData resultSetData =new ResponseData();
		    if (result.hasErrors()) {
		    	data.setUserId(id);
		        return  resultSetData;
		    }
		    repository.save(data);
		    return resultSetData;
		}
		   

	  @DeleteMapping("/delete-user/{id}")
	  void deleteFwUserModel(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	
}
