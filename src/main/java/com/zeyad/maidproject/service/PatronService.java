package com.zeyad.maidproject.service;

import com.zeyad.maidproject.DAO.PatronRepository;
import com.zeyad.maidproject.entity.Patron;
import com.zeyad.maidproject.exception.PatronNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {

    private PatronRepository patronRepository;
    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new PatronNotFoundException("Patron with ID " + id + " not found"));
    }

    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public void deletePatron(Long id) {
        if (!patronRepository.existsById(id)) {
            throw new PatronNotFoundException("Patron with ID " + id + " not found");
        }
        patronRepository.deleteById(id);
    }
}
