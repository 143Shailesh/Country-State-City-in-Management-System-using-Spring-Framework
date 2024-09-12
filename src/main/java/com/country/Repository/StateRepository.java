package com.country.Repository;

import com.country.Entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StateRepository extends JpaRepository<State,Long> {
    State findByName(String name);
}
