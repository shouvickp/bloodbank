package com.technichalgarden.bloodbank.component;

import java.util.List;

import org.springframework.stereotype.Component;

import com.technichalgarden.bloodbank.model.BloodBankSeries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class SeriesGenerator {
	
	@PersistenceContext
	private EntityManager entityManager;

	public synchronized String getSeries(String seriesName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM blood_bank_series WHERE name ='").append(seriesName).append("' FOR UPDATE");
		Query query = entityManager.createNativeQuery(sql.toString(), BloodBankSeries.class);
		List<BloodBankSeries> bloodBankSeriesList = query.getResultList();
		BloodBankSeries bloodBankSeries = null;
		String value = "";
		if(!bloodBankSeriesList.isEmpty()) {
			bloodBankSeries = bloodBankSeriesList.get(0);
			value += bloodBankSeries.getNexSeries();
			bloodBankSeries.setNexSeries(bloodBankSeries.getNexSeries()+1);
		} else {
			bloodBankSeries = new BloodBankSeries();
			bloodBankSeries.setName(seriesName);
			value = "1000001";
			bloodBankSeries.setNexSeries(1000002L);
			entityManager.persist(bloodBankSeries);
		}
		return value;
	}

}
