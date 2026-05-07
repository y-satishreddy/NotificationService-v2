package com.skylimit.Skylimit.repository;

import com.skylimit.Skylimit.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {
   public AppUser findByName(String name);
}
