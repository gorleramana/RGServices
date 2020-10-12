/**
 * 
 */
package com.rg.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.rg.services.model.RGUsers;

/**
 * @author RamanaGorle
 *
 */
@Repository
@EnableJpaRepositories
//@NoRepositoryBean
public interface RGRepository extends JpaRepository<RGUsers, Long> {
	//public List<RGUsers> findByTopicId()
}
