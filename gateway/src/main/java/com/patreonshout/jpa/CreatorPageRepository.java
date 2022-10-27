package com.patreonshout.jpa;

import com.patreonshout.beans.CreatorPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorPageRepository extends JpaRepository<CreatorPage, Long> {
	CreatorPage findByPageUrl(String pageUrl);
}
