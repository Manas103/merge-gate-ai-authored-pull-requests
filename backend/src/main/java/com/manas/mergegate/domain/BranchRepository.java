package com.manas.mergegate.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, String> {
    List<Branch> findByPrId(String prId);
}
