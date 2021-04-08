package com.keita.schoolmanagement.service;

import com.keita.schoolmanagement.model.Address;
import com.keita.schoolmanagement.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    public Long getID(Long id) {
        return addressRepository.findIdById(id);
    }

    public Optional<Address> getUserAddress(Long id) {
        return addressRepository.findById(id);
    }

    public void updateAddress(Long userID, Address address) {
        addressRepository.updateData(address.getStreet(), address.getCity(),
                address.getState(), address.getZipCode(), userID);
    }

    public Address save(Long userID, Address address) {
        addressRepository.save(userID, address.getStreet(),
                address.getCity(), address.getState(), address.getZipCode());
        return address;
    }

}
