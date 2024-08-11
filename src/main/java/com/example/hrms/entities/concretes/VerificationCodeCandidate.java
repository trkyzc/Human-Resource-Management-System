package com.example.hrms.entities.concretes;



import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="verification_code_candidates")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "verification_code_id")
public class VerificationCodeCandidate extends VerificationCode {
	
	
	
	@JoinColumn(name = "candidate_id", referencedColumnName = "user_id")
	private int candidateId;
	

}
