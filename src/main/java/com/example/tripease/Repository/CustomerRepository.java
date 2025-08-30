package com.example.tripease.Repository;
import com.example.tripease.Enum.Gender;
import com.example.tripease.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByGender(Gender gender);

    List<Customer> findByGenderAndAge(Gender gender, int age);

    @Query("SELECT c FROM Customer c WHERE c.gender = :gender AND c.age > :age")
    List<Customer> getAllByGenderAndAgeGreaterBy(@Param("gender") Gender gender, @Param("age") int age);
}

