package com.mtg.mtgservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtg.mtgservice.model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    
}
