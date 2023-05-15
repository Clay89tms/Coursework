//package org.coursework.project_warehouse.service;
//
//import lombok.RequiredArgsConstructor;
//import org.coursework.project_warehouse.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//
//@Service
//public class SecurityDetailsService implements UserDetailsService {
//
//    private final UserRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return repository.findByUsername(username);
//    }
//}
