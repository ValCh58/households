package eis.company.households.repoeisystems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.modeleis.Alarm; 

@Repository
public interface RepoAlarm extends JpaRepository<Alarm, Integer> {

}
