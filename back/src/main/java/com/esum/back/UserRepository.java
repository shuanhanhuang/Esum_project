package com.esum.back;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
	User findByUsername(String username);
}
