package com.training.demoo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.demoo.entity.EmployeeClassEntity;
import com.training.demoo.entity.EmployeeClassRepository;

@Service
public class EmployeeClassService {
	@Autowired
	EmployeeClassRepository repository;
	
	public List<EmployeeClassEntity> getAllEmployees() {
		List<EmployeeClassEntity> result = (List<EmployeeClassEntity>) repository.findAll();
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<EmployeeClassEntity>();
		}
	}

	public EmployeeClassEntity getEmployeeById(Long id) throws Exception {
		Optional<EmployeeClassEntity> employee = repository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new Exception("No employee record exist for given id");
		}
	}

	public void deleteEmployeeById(Long id) throws Exception {
		Optional<EmployeeClassEntity> employee = repository.findById(id);

		if (employee.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new Exception("No employee record exist for given id");
		}
	}

	public EmployeeClassEntity createOrUpdateEmployee(EmployeeClassEntity entity) {
		if (entity.getId() == null) {
			entity = repository.save(entity);
			return entity;
		} else {
			Optional<EmployeeClassEntity> employee = repository.findById(entity.getId());

			if (employee.isPresent()) {
				EmployeeClassEntity newEntity = employee.get();
				newEntity.setEmail(entity.getEmail());
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}
}
