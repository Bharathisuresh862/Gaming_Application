package com.gaming.gamingsystem.services;

import com.gaming.gamingsystem.entities.Members;
import com.gaming.gamingsystem.repository.MembersRepository;
import com.gaming.gamingsystem.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MembersService {
    private static final Logger log = LoggerFactory.getLogger(MembersService.class);
    private final MembersRepository repo;

    public MembersService(MembersRepository repo) {
        this.repo = repo;
    }

    public Members create(Members member) {
        log.info("Creating member: {}", member.getName());
        member.setId(null);
        return repo.save(member);
    }

    public List<Members> findAll() {
        return repo.findAll();
    }

    public Members findById(String id) {
        return repo.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Member not found: " + id));
    }

    public Members update(String id, Members member) {
        Members oldMember = findById(id);
        oldMember.setName(member.getName());
        oldMember.setPhone(member.getPhone());
        oldMember.setBalance(member.getBalance());
        return repo.save(oldMember);
    }

    public boolean delete(String id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Member not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}
