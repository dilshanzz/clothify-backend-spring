package org.example.repository;

import org.example.entity.BillingInfo;
import org.springframework.data.repository.CrudRepository;

public interface BillingInfoRepository extends CrudRepository<BillingInfo,Long> {
}
