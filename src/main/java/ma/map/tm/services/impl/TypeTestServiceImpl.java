package ma.map.tm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.map.tm.dao.TypeTestRepository;
import ma.map.tm.entities.TypeTest;
import ma.map.tm.services.TypeTestService;

@Service
public class TypeTestServiceImpl implements TypeTestService {

	@Autowired
	TypeTestRepository typeTestRepository;
	@Override
	public List<TypeTest> getAllTypeTest() {
		return typeTestRepository.findAll();
	}

}
