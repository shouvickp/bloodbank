package com.technichalgarden.bloodbank.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.technichalgarden.bloodbank.dto.AvaliableBloodStockDTO;
import com.technichalgarden.bloodbank.dto.BloodStockAvalibiltyInfoDTO;
import com.technichalgarden.bloodbank.dto.SearchBloodStockAvalabilityDTO;
import com.technichalgarden.bloodbank.model.QBloodStock;
import com.technichalgarden.bloodbank.model.QHospital;
import com.technichalgarden.bloodbank.repository.SearchBloodStockAvailabilityRepository;

import io.jsonwebtoken.lang.Objects;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class SearchBloodStockAvailabilityRepositoryImpl implements SearchBloodStockAvailabilityRepository {

	private final Logger log = LoggerFactory.getLogger(SearchBloodStockAvailabilityRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BloodStockAvalibiltyInfoDTO> dynamicSearchBloodStockAvalibilityQueryDSL(
			SearchBloodStockAvalabilityDTO searchBloodStockAvalabilityDTO) {

		QHospital qHospital = QHospital.hospital;
		QBloodStock qBloodStock = QBloodStock.bloodStock;

		JPAQuery<Tuple> query = new JPAQuery<>(entityManager);

		query.select(qHospital.id, qHospital.hospitalName, qHospital.addressLine, qHospital.city, qHospital.state,
				qHospital.pincode, qHospital.contactNumber, qHospital.email, qBloodStock.bloodGroup,
				qBloodStock.stockCount).from(qHospital).innerJoin(qBloodStock)
				.on(qHospital.id.eq(qBloodStock.hospitalId)).orderBy(qHospital.id.asc());
		if (null != searchBloodStockAvalabilityDTO.getCity()
				&& !"".equals(searchBloodStockAvalabilityDTO.getCity().trim())) {
			query.where(qHospital.city.eq(searchBloodStockAvalabilityDTO.getCity()));
		}
		if (null != searchBloodStockAvalabilityDTO.getState()
				&& !"".equals(searchBloodStockAvalabilityDTO.getState().trim())) {
			query.where(qHospital.state.eq(searchBloodStockAvalabilityDTO.getState()));
		}
		if (null != searchBloodStockAvalabilityDTO.getBloodGroup()) {
			query.where(qBloodStock.bloodGroup.eq(searchBloodStockAvalabilityDTO.getBloodGroup()));
		}

		List<Tuple> bloodStockList = query.fetch();
		Long hospitalId = 0l;
		List<BloodStockAvalibiltyInfoDTO> responseList = new ArrayList<>();
		List<AvaliableBloodStockDTO> availableBloodStockList = new ArrayList<>();
		BloodStockAvalibiltyInfoDTO bloodStockAvalibiltyInfoDTO = new BloodStockAvalibiltyInfoDTO();
		for (Tuple hospital : bloodStockList) {			
			if (hospital.get(qHospital.id) != hospitalId) {
				if(hospitalId!=0) {
					bloodStockAvalibiltyInfoDTO.setBloodStocks(availableBloodStockList);
					responseList.add(bloodStockAvalibiltyInfoDTO);
					bloodStockAvalibiltyInfoDTO = new BloodStockAvalibiltyInfoDTO();
					availableBloodStockList = new ArrayList<>();
				}
				bloodStockAvalibiltyInfoDTO.setHospitalId(hospital.get(qHospital.id));
				bloodStockAvalibiltyInfoDTO.setHospitalName(hospital.get(qHospital.hospitalName));
				bloodStockAvalibiltyInfoDTO.setHospitalEmail(hospital.get(qHospital.email));
				bloodStockAvalibiltyInfoDTO.setHospitalContactNo(hospital.get(qHospital.contactNumber));
				bloodStockAvalibiltyInfoDTO.setHospitalAddress(
						hospital.get(qHospital.addressLine) + ", " + hospital.get(qHospital.city) + ", "
								+ hospital.get(qHospital.state) + ". Pincode - " + hospital.get(qHospital.pincode));
				hospitalId = hospital.get(qHospital.id);
			}
			AvaliableBloodStockDTO avaliableBloodStockDTO = new AvaliableBloodStockDTO();
			avaliableBloodStockDTO.setBloodGroup(hospital.get(qBloodStock.bloodGroup));
			avaliableBloodStockDTO.setStockCount(hospital.get(qBloodStock.stockCount));
			availableBloodStockList.add(avaliableBloodStockDTO);
			
		}
		if(!bloodStockList.isEmpty()) {
			bloodStockAvalibiltyInfoDTO.setBloodStocks(availableBloodStockList);
			responseList.add(bloodStockAvalibiltyInfoDTO);
		}
		return responseList;

	}
}
