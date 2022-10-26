package com.patreonshout.jpa;

import com.patreonshout.beans.WebAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebAccountRepository extends JpaRepository<WebAccount, Long> {
	WebAccount findByUsername(String username);
	WebAccount findByLoginToken(String loginToken);
}
