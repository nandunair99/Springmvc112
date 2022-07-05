package com.narola.spring.repository;

import com.narola.spring.Customer;
import com.narola.spring.CustomerPK;
import com.narola.spring.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, CustomerPK> {


}
