package com.patreonshout.jpa;

import com.patreonshout.beans.WebAccountBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewWebAccountRepository extends JpaRepository<WebAccountBean, Long> {
	List<WebAccountBean> findByUsername(String username);
}
