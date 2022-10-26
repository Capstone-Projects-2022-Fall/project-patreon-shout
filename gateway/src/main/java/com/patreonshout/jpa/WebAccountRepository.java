package com.patreonshout.jpa;

import com.patreonshout.beans.WebAccountBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebAccountRepository extends JpaRepository<WebAccountBean, Long> {
	WebAccountBean findByUsername(String username);
	WebAccountBean findByLoginToken(String loginToken);
}
