package com.example.Voertuig.serviceImpl;

import com.example.Voertuig.domain.Customer;
import com.example.Voertuig.domain.ERole;
import com.example.Voertuig.domain.Role;
import com.example.Voertuig.repository.CustomerRepository;
import com.example.Voertuig.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserCreator {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void addFirstUser() {
        Optional<Role> optionalRole = roleRepository.findByName(ERole.ROLE_ADMIN);
        Role userRole = optionalRole.get();
        Customer customer = new Customer("admin","admin@admin.com", passwordEncoder.encode("123456"), "Kerkstraat 1", "2211SS", "Netherlands");
        Set<Role> roles = Stream.of(userRole)
                .collect(Collectors.toCollection(HashSet::new));
        customer.setRoles(roles);
        customerRepository.save(customer);

    }
}
