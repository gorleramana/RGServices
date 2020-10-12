/**
 * 
 */
package com.rg.services.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rg.services.exception.ResourceNotFoundException;
import com.rg.services.model.RGUsers;
import com.rg.services.repository.RGRepository;

/**
 * @author RamanaGorle
 *
 */
@RestController
@RequestMapping("/rgapi/v1/")
public class RGController {

	@Autowired//(required=true)
	private RGRepository rgRepository;

	/**
	 * This method is get all the RGusers
	 * 
	 * @return
	 */
	@GetMapping("rgusers")
	public List<RGUsers> getAllRGUsers() {
		return (List<RGUsers>) rgRepository.findAll();

	}

	/**
	 * This method is to get a specific user
	 * 
	 * @param rgUserId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("rgusers/{id}")
	public ResponseEntity<RGUsers> getRGUserById(@PathVariable(value = "id") Long rgUserId)
			throws ResourceNotFoundException {
		RGUsers rgUser = rgRepository.findById(rgUserId)
				.orElseThrow(() -> new ResourceNotFoundException("RGUsers is not found for this id" + rgUserId));

		return ResponseEntity.ok().body(rgUser);
	}

	/**
	 * THis method is to create RGUsers
	 * 
	 * @param rgUser
	 * @return
	 */
	@PostMapping("rgusers")
	public RGUsers createRGUser(@RequestBody RGUsers rgUser) {
		return this.rgRepository.save(rgUser);
	}

	/**
	 * This method is to update the RGUsers
	 * 
	 * @param rgUserId
	 * @param rgUserDetails
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("rgusers/{id}")
	public ResponseEntity<RGUsers> updateRGUser(@PathVariable(value = "id") Long rgUserId,
			@Valid @RequestBody RGUsers rgUserDetails) throws ResourceNotFoundException {
		RGUsers rgUser = rgRepository.findById(rgUserId)
				.orElseThrow(() -> new ResourceNotFoundException("RGUsers is not found for this id" + rgUserId));
		rgUser.setFirstname(rgUserDetails.getFirstname());
		rgUser.setLastname(rgUserDetails.getLastname());
		rgUser.setEmail(rgUserDetails.getEmail());
		return ResponseEntity.ok(this.rgRepository.save(rgUser));
	}

	/**
	 * This method is to delete the RGUsers
	 * 
	 * @param rgUserId
	 * @param rgUserDetails
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("rgusers/{id}")
	public Map<String, Boolean> deleteRGUser(@PathVariable(value = "id") Long rgUserId,
			@Valid @RequestBody RGUsers rgUserDetails) throws ResourceNotFoundException {
		RGUsers rgUser = rgRepository.findById(rgUserId)
				.orElseThrow(() -> new ResourceNotFoundException("RGUsers is not found for this id" + rgUserId));

		this.rgRepository.delete(rgUser);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
