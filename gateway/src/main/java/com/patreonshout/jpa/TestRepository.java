package com.patreonshout.jpa;

import com.patreonshout.beans.NewWebAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<NewWebAccount, Long> {

}
