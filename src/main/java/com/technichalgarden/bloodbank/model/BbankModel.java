package com.technichalgarden.bloodbank.model;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BbankModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@CreatedDate
	@Column(name = "creation_ts", nullable = false, updatable = false)
	protected Instant creationTs;

	@CreatedBy
	@Column(name = "created_by", nullable = false, updatable = false)
	protected String createdBy;

	@LastModifiedDate
	@Column(name = "last_modification_ts")
	protected Instant lastModificationTs;

	@LastModifiedBy
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;

	@Version
	@Column(name = "version")
	protected Long version;

	public Instant getCreationTs() {
		return creationTs;
	}

	public void setCreationTs(Instant creationTs) {
		this.creationTs = creationTs;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getLastModificationTs() {
		return lastModificationTs;
	}

	public void setLastModificationTs(Instant lastModificationTs) {
		this.lastModificationTs = lastModificationTs;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
